package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.*
import bio.kinetiqa.core.http.Functions

fun Route.authRouting() {
	route("/sign.up") {
		post {
			Functions.postParams(call);
		}
	}

	route("/sign.in") {
		post {
			Functions.getParams(call);
		}
	}
}