package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.StringJoiner
import kotlin.system.exitProcess

fun Route.authRouting() {
	route("/sign.up") {
		get {
			val get: Map<String, String> = Params.get(call)
			call.respond(HttpStatusCode.OK, "Get sign up ${get.toString()}\n")
		}
	}

	route("/sign.in") {
		get {
			val get: Map<String, String> = Params.get(call)
			call.respond(HttpStatusCode.OK, "Get sign in ${get.toString()}\n")
		}

		post {
			val get: Map<String, String> = Params.get(call)
			val postBody: String = call.receiveText()
		}
	}
}