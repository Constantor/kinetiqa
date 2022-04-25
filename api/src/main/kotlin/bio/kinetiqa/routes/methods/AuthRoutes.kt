package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import io.ktor.http.*
import io.ktor.server.response.*

fun Route.authRouting() {
	route("/sign.up") {
		get {
			val get: Map<String, String> = Params.get(call)
			call.respond(HttpStatusCode.OK, "Signed up")
		}
	}

	route("/sign.in") {
		post {
			val post: Map<String, String> = Params.post(call)
			call.respond(HttpStatusCode.OK, "Signed in")
		}
	}
}