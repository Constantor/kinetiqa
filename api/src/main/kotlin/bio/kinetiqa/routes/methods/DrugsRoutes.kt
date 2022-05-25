package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.model.dataclasses.Drug
import bio.kinetiqa.model.tables.Drugs
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val gson = Gson()
			val get: Map<String, String> = Params.get(call)
			val out = transaction {
				addLogger(StdOutSqlLogger)
				Drug.all().map { drug -> gson.toJson(drug) }
			}
			call.respond(HttpStatusCode.OK, out)
		}
	}
}
