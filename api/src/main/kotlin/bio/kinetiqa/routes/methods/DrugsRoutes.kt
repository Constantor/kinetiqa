package bio.kinetiqa.routes.methods

import bio.kinetiqa.model.entities.Course
import bio.kinetiqa.model.entities.Drug
import bio.kinetiqa.model.entities.Intake
import bio.kinetiqa.model.sessions.UserSession
import bio.kinetiqa.model.tables.Courses
import bio.kinetiqa.model.tables.Drugs
import bio.kinetiqa.model.tables.Intakes
import bio.kinetiqa.model.tables.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.ZoneOffset

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val out = transaction {
				Drug.all().orderBy(Drugs.labelName to SortOrder.ASC).toList()
			}
			call.respond(HttpStatusCode.OK, out)
		}
	}

	authenticate("auth-session") {
		route("/intakes.add") {
			post {
				val curUserId = call.principal<UserSession>()!!.userId
				try {
					val params = call.receiveParameters()
					val curDrugId = params["drug_id"]!!.toInt()
					transaction {
						Intake.new {
							userId = EntityID(curUserId, Users)
							drugId = EntityID(curDrugId, Drugs)
							massIntookMg = Drug[curDrugId].standardDosageMG.toInt()
							timeWhen = LocalDateTime.now().toInstant(ZoneOffset.UTC)
						}
					}
				} catch(e: NullPointerException) {
					call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
					return@post
				}
				call.respond(HttpStatusCode.OK, "Intake add successful")
			}
		}

		route("/intakes.list") {
			get {
				val curUserId = call.principal<UserSession>()!!.userId
				val out: List<Intake>
				try {
					val params = call.receiveParameters()
					val curDrugId = params["drug_id"]!!.toInt()
					out = transaction {
						Intakes.select((Intakes.userId eq curUserId) and (Intakes.drugId eq curDrugId))
							.orderBy(Intakes.timeWhen to SortOrder.ASC)
							.map { row -> Intake.wrapRow(row) }.filter { intake ->
								intake.timeWhen.isAfter(
									LocalDateTime.now().minusWeeks(3).toInstant(ZoneOffset.UTC)
								)
							}.toList()
					}
				} catch(e: NullPointerException) {
					call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
					return@get
				}
				call.respond(HttpStatusCode.OK, out)
			}
		}
	}
}
