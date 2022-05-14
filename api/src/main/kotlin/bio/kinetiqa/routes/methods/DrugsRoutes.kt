package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.models.Drugs
import io.ktor.http.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val get: Map<String, String> = Params.get(call)
			//call.respond(HttpStatusCode.OK, "List\n${get}")
			var outText: String = ""
			transaction {
				addLogger(StdOutSqlLogger)
				val query: Query = Drugs.selectAll()
				outText = query.toList().toString()
			}
			call.respond(HttpStatusCode.OK, outText)
		}
	}
}
