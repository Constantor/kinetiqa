package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.model.entities.Course
import bio.kinetiqa.model.entities.Drug
import bio.kinetiqa.model.entities.User
import bio.kinetiqa.model.sessions.UserSession
import bio.kinetiqa.model.tables.Courses
import bio.kinetiqa.model.tables.Drugs
import bio.kinetiqa.model.tables.Users
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun Route.drugsRouting() {
	route("/drugs.list") {
		get {
			val out = transaction {
				addLogger(StdOutSqlLogger)
				Drug.all().toList()
			}
			call.respond(HttpStatusCode.OK, out)
		}
	}

	authenticate("auth-session") {
		route("/user.drugs.add") {
			post {
				val curUserId = call.principal<UserSession>()!!.userId
				try {
					val params = call.receiveParameters()
					val curDrugId = params["drug_id"]!!.toInt()
					val exists = !transaction {
						Courses.select((Courses.userId eq curUserId) and (Courses.drugId eq curDrugId)).empty()
					}
					if (exists) {
						call.respond(HttpStatusCode.BadRequest, "User already has this course")
					}
					transaction {
						Course.new {
							userId = EntityID(curUserId, Users)
							drugId = EntityID(curDrugId, Drugs)
							whenAdded = LocalDateTime.now().toInstant(ZoneOffset.UTC)
						}
					}
				} catch (e: NullPointerException) {
					call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
				}
				call.respond(HttpStatusCode.OK, "Course add successful")
			}
		}

		route("/user.drugs.delete") {
			post {
				val curUserId = call.principal<UserSession>()!!.userId
				try {
					val params = call.receiveParameters()
					val curDrugId = params["drug_id"]!!.toInt()
					transaction {
						Courses.deleteWhere{(Courses.userId eq curUserId) and (Courses.drugId eq curDrugId)}
					}
				} catch (e: NullPointerException) {
					call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
				}
				call.respond(HttpStatusCode.OK, "Course delete successful")
			}
		}

		route("/user.drugs.list") {
			get {
				val curUserId = call.principal<UserSession>()!!.userId
				val out = transaction {
					addLogger(StdOutSqlLogger)
					Drugs.join(Courses, JoinType.INNER, additionalConstraint = {(Courses.drugId eq Drugs.id) and (Courses.userId eq curUserId)}).selectAll().toList()
				}
				call.respond(HttpStatusCode.OK, out)
			}
		}
	}
}
