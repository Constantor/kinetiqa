package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.model.tables.Drugs
import io.ktor.http.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val get: Map<String, String> = Params.get(call)
			// call.respond(HttpStatusCode.OK, "List\n${get}")
			val out = transaction {
				addLogger(StdOutSqlLogger)
				Drugs.selectAll().toList()
			}
			call.respond(HttpStatusCode.OK, out.toString())
		}
	}
}
