package bio.kinetiqa.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import bio.kinetiqa.routes.methods.*

fun Application.configureRouting() {
	routing {
		route("/method") {
			authRouting()
			drugsRouting()
		}
	}
}
