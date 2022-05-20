package bio.kinetiqa.routes.methods

import bio.kinetiqa.core.DatabaseFactory.database
import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.model.tables.Drugs
import io.ktor.http.*
import io.ktor.server.response.*
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val get: Map<String, String> = Params.get(call)
			val out = database.sequenceOf(Drugs).toList()
			call.respond(HttpStatusCode.OK, out)
		}
	}
}
