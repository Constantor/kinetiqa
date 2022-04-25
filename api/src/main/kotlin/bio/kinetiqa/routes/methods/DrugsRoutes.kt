package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.*
import bio.kinetiqa.core.http.Functions

fun Route.drugsRouting() {
	route("/method/drugs.list") {
		get {
			Functions.paramsReduce(call.request.queryParameters.toMap());
		}

		post {
			call.parameters
		}
	}
}