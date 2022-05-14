package bio.kinetiqa.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*

// import com.google.gson.Gson
// import kotlinx.serialization.json.Json
// import io.ktor.serialization.kotlinx.json.*

fun Application.configureSerialization() {
	install(ContentNegotiation) {
		/*json(Json {
			prettyPrint = true
			isLenient = true
		})*/
		gson()
	}
}
