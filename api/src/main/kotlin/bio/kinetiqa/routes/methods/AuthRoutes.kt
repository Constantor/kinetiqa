package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.http.Functions

fun Route.authRouting() {
	route("/sign.up") {
		post {
			val get: Map<String, String> = getParams(call)
		}
	}

	route("/sign.in") {
		post {
			val post: Map<String, String> = postParams(call)
		}
	}
}