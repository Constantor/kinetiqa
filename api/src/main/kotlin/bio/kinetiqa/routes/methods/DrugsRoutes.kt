package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.*
import bio.kinetiqa.core.utils.Params

fun Route.drugsRouting() {
	route("/method/drugs.list") {
		get {
			Params.get(call);
		}

		post {
			Params.post(call);
		}
	}
}