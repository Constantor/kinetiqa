package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params

fun Route.authRouting() {
	route("/sign.up") {
		post {
			val get: Map<String, String> = Params.get(call)
		}
	}

	route("/sign.in") {
		post {
			val post: Map<String, String> = Params.post(call)
		}
	}
}