package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import io.ktor.http.*
import io.ktor.server.response.*

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val get: Map<String, String> = Params.get(call)
			call.respond(HttpStatusCode.OK, "List\n${get}")
		}
	}
}
