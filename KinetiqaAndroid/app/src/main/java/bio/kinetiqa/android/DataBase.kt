package bio.kinetiqa.android

import TrustAllX509TrustManager
import android.os.Environment
import bio.kinetiqa.android.model.db.entites.Drug
import com.github.mikephil.charting.data.Entry
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext


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
                jackson()
            }
        }

        private lateinit var substances: HashMap<Int, Drug>
        private var lastUpdated: LocalDateTime = LocalDateTime.MIN
        private var userSession =
            "a62376260425680072af1384a84aeb89/47edc71d6e70c087f496b08c2772f3b1f5a3660d61284ad854e65a8e36e34d0826f4c1e86a617b12362d3a0b2a40ad842aff8e084b5d52032a4f154207187d46ac329969800a0245833cc1f9cc91e334:6925232e3f4b2ab63e335c79d03b8d278bcc3e633d2d9a4ceb8db9f9d9d7ecc6"
        private const val userSessionHeaderName = "user_session"
        private var graphInfoID: MutableSet<Int> = HashSet()

        @JvmField
        var notifyStatus = getNotifications()

        private fun needToUpdate(): Boolean {
            return lastUpdated.plusDays(1).isBefore(LocalDateTime.now())
        }

        private fun updateSubstances() {
            runBlocking {
                val response: List<Drug> = client.get {
                    url {
                        path("drugs.list")
                    }
                }.body()
                substances = HashMap()
                for (drug in response) {
                    substances[drug.id] = drug
                }
            }
            lastUpdated = LocalDateTime.now()
        }

        fun getNotifications(): HashMap<Int, Boolean> {
            return HashMap()
            //TODO
        } // Вернуть мапу всех id лекарств пользователя и поставлены ли на них нотификации


        fun addNotificationToBase(state: Substance?) {
            //TODO
        } //Добавить нотификацию для данного вещества. Можно получить id по state.getResourceID()


        fun deleteNotificationFromBase(state: Substance?) {
            //TODO
        } // Удалить нотификацию для данного вещества.


        fun addSubstanceToBase(state: Substance) {
            runBlocking {
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

        fun deleteSubstanceFromBase(state: Substance) {
            runBlocking {
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
            val entries = ArrayList<Entry>()
            entries.add(Entry(1f, 5f))
            entries.add(Entry(2f, 2f))
            entries.add(Entry(3f, 1f))
            entries.add(Entry(4f, -3f))
            entries.add(Entry(5f, 4f))
            entries.add(Entry(6f, 1f))
            return entries
            //TODO
        } // по id лекарства получить список точек графика приема - координат графика - (ось X, ось Y)


        fun addSubstanceOnGraph(state: Substance) {
            graphInfoID.add(state.resourceID)
        }

        fun deleteSubstanceFromGraph(state: Substance) {
            graphInfoID.remove(state.resourceID)
        }

        fun getMainSubstanceBase(): ArrayList<Substance> {
            val products = ArrayList<Substance>()
            if (needToUpdate()) {
                updateSubstances()
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

        fun takingMedication(resourceId: Int) {
            //TODO
        } // добавить запись о приеме лекарства (Только что увидел, что не добавил кнопку про приём, но это 5 минут работы и будет сделано)

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
    }
}
