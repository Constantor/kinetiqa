package bio.kinetiqa.android

import TrustAllX509TrustManager
import bio.kinetiqa.android.model.db.entites.Drug
import bio.kinetiqa.android.model.db.entites.Intake
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mikephil.charting.data.Entry
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.http.HttpException
import java.security.SecureRandom
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.net.ssl.SSLContext
import kotlin.math.pow

class SessionExpiredException : HttpException("Session expired, please log in")

class DataBase {
    companion object Methods {
        private val client = HttpClient(Apache) {
            engine {
                sslContext = SSLContext.getInstance("TLS")
                    .apply {
                        init(null, arrayOf(TrustAllX509TrustManager()), SecureRandom())
                    }
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "kinetiqa.bio"
                    path("method/")
                }
            }
            install(ContentNegotiation) {
                jackson {
                    findAndRegisterModules()
                }
            }
            HttpResponseValidator {
                validateResponse { response ->
                    if (response.status != HttpStatusCode.OK) {
                        if (response.status == HttpStatusCode.Unauthorized) {
                            throw SessionExpiredException()
                        }
                        throw HttpException("Couldn't reach server")
                    }
                }
            }
        }

        private lateinit var substances: HashMap<Int, Drug>
        private var lastUpdated: LocalDateTime = LocalDateTime.MIN
        private var userSession =
            "a62376260425680072af1384a84aeb89/47edc71d6e70c087f496b08c2772f3b1f5a3660d61284ad854e65a8e36e34d0826f4c1e86a617b12362d3a0b2a40ad842aff8e084b5d52032a4f154207187d46ac329969800a0245833cc1f9cc91e334:6925232e3f4b2ab63e335c79d03b8d278bcc3e633d2d9a4ceb8db9f9d9d7ecc6"
        private const val userSessionHeaderName = "user_session"
        private var graphInfoID: MutableSet<Int> = HashSet()
        private lateinit var notifyStatus: HashMap<Int, Boolean>

        init {
            runBlocking {
                launch { updateSubstances() }
                launch { getNotifications() }
            }
        }

        private fun needToUpdate(): Boolean {
            return lastUpdated.plusDays(1).isBefore(LocalDateTime.now())
        }

        private suspend fun updateSubstances() {
            coroutineScope {
                launch {
                    val response: List<Drug> = client.get {
                        url {
                            path("drugs.list")
                        }
                    }.body()
                    substances = HashMap()
                    for (drug in response) {
                        substances[drug.id] = drug
                    }
                    lastUpdated = LocalDateTime.now()
                }
            }
        }

        fun getNotifications(): HashMap<Int, Boolean> {
            notifyStatus = HashMap()
            runBlocking {
                launch {
                    val raw: Map<Int, Boolean> = client.get {
                        url {
                            path("notifications.list")
                        }
                        headers.append(userSessionHeaderName, userSession)
                    }.body()
                    notifyStatus = HashMap(raw)
                }
            }
            return notifyStatus
        }

        fun addNotificationToBase(state: Substance) {
            notifyStatus[state.resourceID] = false
            runBlocking {
                launch {
                    client.post {
                        url {
                            path("notifications.add")
                        }
                        headers.append(userSessionHeaderName, userSession)
                        setBody(FormDataContent(
                            Parameters.build {
                                append("drug_id", state.resourceID.toString())
                            }
                        ))
                    }
                }
            }
        }

        fun deleteNotificationFromBase(state: Substance) {
            notifyStatus[state.resourceID] = true
            runBlocking {
                launch {
                    client.post {
                        url {
                            path("notifications.delete")
                        }
                        headers.append(userSessionHeaderName, userSession)
                        setBody(FormDataContent(
                            Parameters.build {
                                append("drug_id", state.resourceID.toString())
                            }
                        ))
                    }
                }
            }
        }

        fun addUserSubstance(state: Substance) {
            runBlocking {
                launch {
                    client.post {
                        url {
                            path("courses.add")
                        }
                        headers.append(userSessionHeaderName, userSession)
                        setBody(FormDataContent(
                            Parameters.build {
                                append("drug_id", state.resourceID.toString())
                            }
                        ))
                    }
                }
            }
        }

        fun deleteUserSubstance(state: Substance) {
            runBlocking {
                launch {
                    client.post {
                        url {
                            path("courses.delete")
                        }
                        headers.append(userSessionHeaderName, userSession)
                        setBody(FormDataContent(
                            Parameters.build {
                                append("drug_id", state.resourceID.toString())
                            }
                        ))
                    }
                }
            }
        }

        fun getListOfSubstances(): MutableList<Substance> {
            val userSubstances: MutableList<Substance> = ArrayList()
            runBlocking {
                val drugIds: List<Int> = client.get {
                    url {
                        path("courses.list")
                    }
                    headers.append(userSessionHeaderName, userSession)
                }.body()
                if (needToUpdate()) {
                    updateSubstances()
                } else {
                    for (id in drugIds) {
                        if (!substances.containsKey(id)) {
                            updateSubstances()
                            break
                        }
                    }
                }
                for (id in drugIds) {
                    userSubstances.add(Substance(substances[id]!!))
                }
            }
            return userSubstances
        }

        fun getGraphLine(subId: Int): ArrayList<Entry> {
            val intakes: List<Intake>
            runBlocking {
                intakes = client.get {
                    url {
                        path("intakes.list")
                    }
                    headers.append(userSessionHeaderName, userSession)
                    setBody(FormDataContent(
                        Parameters.build {
                            append("drug_id", subId.toString())
                        }
                    ))
                }.body()
            }
            val drug = substances[subId]!!
            val map: Map<String, Long> = ObjectMapper().readValue(
                drug.kineticsPlot,
                object : TypeReference<Map<String, Long>>() {})
            val tmax = map["T_max"]!!
            val thalf = map["T_1/2"]!!
            val step = 10.0
            var curTime = LocalDateTime.now().minusWeeks(3).toEpochSecond(ZoneOffset.UTC) / 60.0
            val begTime = LocalDateTime.now().minusHours(18).toEpochSecond(ZoneOffset.UTC) / 60.0 - curTime
            val endTime = LocalDateTime.now().plusHours(18).toEpochSecond(ZoneOffset.UTC) / 60.0 - curTime
            val stamps = ArrayList<Pair<Double, Double>>()
            for (i in intakes) {
                stamps.add(
                    Pair(
                        i.massIntookMg.toDouble() / drug.standardDosageMG.toDouble(),
                        i.timeWhen.epochSecond / 60 + tmax - curTime
                    )
                )
            }
            val entries = ArrayList<Entry>()
            if (stamps.isEmpty())
                return entries
            var ind = 0
            var sum = 0.0
            curTime = 0.0
            while (curTime < endTime) {
                var next = curTime
                if(curTime < begTime) {
                    next += step * 10
                } else {
                    next += step
                }
                var add = 0.0
                if(ind < stamps.size && next > stamps[ind].second) {
                    next = stamps[ind].second
                    add = stamps[ind].first
                    ind += 1
                }
                sum /= 2.0.pow((next - curTime) / thalf)
                sum += add
                if(curTime >= begTime) {
                    entries.add(Entry(((curTime - begTime) / (60 * 24)).toFloat(), sum.toFloat()))
                }
                curTime = next
            }
            return entries
        }

        fun getGraphInfoId(): MutableSet<Int> {
            return graphInfoID
        }

        fun addSubstanceOnGraph(state: Substance) {
            graphInfoID.add(state.resourceID)
        }

        fun deleteSubstanceFromGraph(state: Substance) {
            graphInfoID.remove(state.resourceID)
        }

        fun getMainSubstanceBase(): ArrayList<Substance> {
            val products = ArrayList<Substance>()
            if (needToUpdate()) {
                runBlocking {
                    updateSubstances()
                }
            }
            for (drug in substances.values) {
                products.add(Substance(drug))
            }
            return products
        }


        fun notificationStatus(resourceID: Int): Boolean {
            return notifyStatus[resourceID] ?: return false
        }

        fun graphStatus(resourceId: Int): Boolean {
            return graphInfoID.contains(resourceId)
        }

        fun addTakeMedicine(state: Substance) {
            runBlocking {
                launch {
                    client.post {
                        url {
                            path("intakes.add")
                        }
                        headers.append(userSessionHeaderName, userSession)
                        setBody(FormDataContent(
                            Parameters.build {
                                append("drug_id", state.resourceID.toString())
                            }
                        ))
                    }
                }
            }
        }

        fun signUp(email: String, password: String) {
            runBlocking {
                val responce = client.post {
                    url {
                        path("sign.up")
                    }

                    setBody(FormDataContent(
                        Parameters.build {
                            append("email", email)
                            append("password", password)
                        }
                    ))
                }
                userSession = responce.headers["session_id"]!!
            }
        }

        fun signIn(email: String, password: String) {
            runBlocking {
                val responce = client.post {
                    url {
                        path("sign.in")
                    }

                    setBody(FormDataContent(
                        Parameters.build {
                            append("email", email)
                            append("password", password)
                        }
                    ))
                }
                userSession = responce.headers["session_id"]!!
            }
        }

        fun logout() {
            userSession = ""
        }

        fun getSubstanceFromId(subID: Int): Substance {
            return Substance(substances[subID]!!)
        }
    }
}
