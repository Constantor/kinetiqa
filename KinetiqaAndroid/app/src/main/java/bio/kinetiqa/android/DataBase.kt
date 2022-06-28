package bio.kinetiqa.android

import TrustAllX509TrustManager
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
import kotlinx.coroutines.runBlocking
import java.security.SecureRandom
import javax.net.ssl.SSLContext


class DataBase {
    companion object Methods {
        val client = HttpClient(Apache) {
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
            install(Logging)
        }
        var SessionId = "ba1d349a18b792d44d0377d5f0f60417/13314a53504e4bde2ba75aef1f927a6d7496a084c6a8fecf8619dc8e948c94f9"
        //TODO
        var graphInfoID: MutableSet<Int> = HashSet()
        @JvmField
        var notifyStatus = getNotifications()

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


        fun addSubstanceToBase(state: Substance?) {
            //TODO
        } // Вся информация про лекарство в класс state. Добавить вещество пользователю


        fun deleteSubstanceFromBase(state: Substance?) {
            //TODO
        } // Удалить лекарство для пользователя


        fun getListOfSubstances(): MutableList<Substance> {
            val substances: MutableList<Substance> = ArrayList()
            val desc1 =
                "Препарат предназначен для симптоматической терапии, уменьшения боли и воспаления на момент использования, на прогрессирование заболевания не влияет."
            substances.add(Substance("Нимесулид", desc1, R.drawable.nayz, 5))
            substances.add(
                Substance(
                    "Каменный уголь 6",
                    "какое-то описание",
                    R.drawable.test_photo,
                    6
                )
            )
            substances.add(
                Substance(
                    "Каменный уголь 7",
                    "какое-то описание",
                    R.drawable.test_photo,
                    7
                )
            )
            substances.add(
                Substance(
                    "Каменный уголь 8",
                    "какое-то описание",
                    R.drawable.test_photo,
                    8
                )
            )
            return substances
            //TODO
        } // Вернуть список всех лекарств пользователя. Как создавать Объект класса Substance видно


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
            runBlocking {
                val response: List<Drug> = client.get {
                    url {
                        path("drugs.list")
                    }
                }.body()
                for(drug in response) {
                    products.add(Substance(drug))
                }
            }
            return products
        } // Список лекарств, которые поддерживает наше приложение, и которые пользователь может добавить себе


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
                val responce = client.submitForm {
                    url {
                        path("sign.up")
                    }
                    formData {
                        append("email", email)
                        append("password", password)
                    }
                }
                SessionId = responce.headers["session_id"]!!
            }
        }

        fun login(email: String, password: String) {
            runBlocking {
                val responce = client.submitForm {
                    url {
                        path("sign.in")
                    }
                    formData {
                        append("email", email)
                        append("password", password)
                    }
                }
                SessionId = responce.headers["session_id"]!!
            }
        }
    }
}
