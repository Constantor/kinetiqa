package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params

fun Route.drugsRouting() {
	route("/method/drugs.list") {
		get {
			val get: Map<String, String> = Params.get(call)
		}
	}
}