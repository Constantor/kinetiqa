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
				addLogger(StdOutSqlLogger)
				Drugs.selectAll().map { row -> Drug(row[Drugs.id], row[Drugs.labelName], row[Drugs.iupac], row[Drugs.description], row[Drugs.kineticsPlot], row[Drugs.photoURL], row[Drugs.standardDosageMG], row[Drugs.dosageStepMG]) }
			}
			call.respond(HttpStatusCode.OK, "${out}\n${System.getenv("DB_HOST").takeUnless { it.isNullOrEmpty() } ?: "kek"}")
		}
	}
}
