package bio.kinetiqa.routes.methods

import bio.kinetiqa.model.entities.Drug
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val out = transaction {
				Drug.all().toList()
			}
			call.respond(HttpStatusCode.OK, out)
		}
	}
}
