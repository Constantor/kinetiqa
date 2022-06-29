package bio.kinetiqa.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import bio.kinetiqa.routes.methods.*
import io.ktor.http.*
import io.ktor.server.response.*

fun Application.configureRouting() {
	routing {
		route("/method") {
			authRouting()
			drugsRouting()
			coursesRouting()
			notificationsRouting()
			route("/method/{...}") {
				handle {
					call.respond(HttpStatusCode.NotFound)
				}
			}
		}

		route("/{...}") {
			handle {
				call.respond(HttpStatusCode.Forbidden)
			}
		}
	}
}
