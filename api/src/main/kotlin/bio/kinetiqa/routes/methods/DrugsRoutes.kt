package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.model.dataclasses.Drug
import bio.kinetiqa.model.tables.Drugs
import io.ktor.http.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

// import com.google.gson.Gson

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val get: Map<String, String> = Params.get(call)
			val out = transaction {
				addLogger(StdOutSqlLogger)
				Drugs.selectAll().map { mapOf(Drugs.id.name to it[Drugs.id], Drugs.label_name.name to it[Drugs.label_name]) }
			}
			call.respond(HttpStatusCode.OK, out)
		}
	}
}
