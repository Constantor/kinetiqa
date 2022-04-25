package bio.kinetiqa.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import bio.kinetiqa.routes.methods.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureRouting() {
	routing {
		route("/method") {
			authRouting()
			drugsRouting()
		}

		route("/{...}") {
			handle {
				call.respond(HttpStatusCode.Forbidden)
			}
		}
	}
}
