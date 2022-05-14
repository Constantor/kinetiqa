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

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val get: Map<String, String> = Params.get(call)
			val out = transaction {
				// addLogger(StdOutSqlLogger)
				Drugs.selectAll().map { Drug(it[Drugs.id], it[Drugs.labelName], it[Drugs.iupac], it[Drugs.description], it[Drugs.kineticsPlot], it[Drugs.photoURL], it[Drugs.standardDosageMG], it[Drugs.dosageStepMG]) }
			}
			call.respond(HttpStatusCode.OK, out)
		}
	}
}
