package bio.kinetiqa.routes.methods

import bio.kinetiqa.model.entities.Course
import bio.kinetiqa.model.entities.Drug
import bio.kinetiqa.model.entities.Schedule
import bio.kinetiqa.model.sessions.UserSession
import bio.kinetiqa.model.tables.Courses
import bio.kinetiqa.model.tables.Drugs
import bio.kinetiqa.model.tables.Schedules
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
import java.time.LocalTime
import java.time.ZoneOffset

fun Route.notificationsRouting() {
    authenticate("auth-session") {
        route("/notifications.add") {
            post {
                val curUserId = call.principal<UserSession>()!!.userId
                try {
                    val params = call.receiveParameters()
                    val curDrugId = params["drug_id"]!!.toInt()
                    transaction {
                        Schedule.new {
                            userId = EntityID(curUserId, Users)
                            drugId = EntityID(curDrugId, Drugs)
                            whenTaken = LocalDateTime.now().toInstant(ZoneOffset.UTC)
                            dosageMg = Drug[curDrugId].standardDosageMG
                            weekDay = LocalDateTime.now().dayOfWeek.value.toShort()
                            timeOfDay = LocalTime.now()
                        }
                    }
                } catch (e: NullPointerException) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
                    return@post
                }
                call.respond(HttpStatusCode.OK, "Notification add successful")
            }
        }

        route("/notifications.delete") {
            post {
                val curUserId = call.principal<UserSession>()!!.userId
                try {
                    val params = call.receiveParameters()
                    val curDrugId = params["drug_id"]!!.toInt()
                    transaction {
                        Schedules.deleteWhere { (Courses.userId eq curUserId) and (Courses.drugId eq curDrugId) }
                    }
                } catch (e: NullPointerException) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
                    return@post
                }
                call.respond(HttpStatusCode.OK, "Notification delete successful")
            }
        }

        route("/notifications.list") {
            get {
                val curUserId = call.principal<UserSession>()!!.userId
                val out = transaction {
                    val userDrugs =
                        Courses.select(Courses.userId eq curUserId).map { row -> row[Courses.drugId] }
                            .toList()
                    val ans = HashMap<Int, Boolean>()
                    for (drug in userDrugs) {
                        ans[drug.value] =
                            !Schedules.select((Schedules.userId eq curUserId) and (Schedules.drugId eq drug.value))
                                .empty()
                    }
                    ans
                }
                call.respond(HttpStatusCode.OK, out)
            }
        }
    }
}