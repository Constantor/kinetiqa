package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.model.entities.User
import bio.kinetiqa.model.sessions.UserSession
import bio.kinetiqa.model.tables.Courses
import bio.kinetiqa.model.tables.Users
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

fun Route.authRouting() {
    route("/sign.up") {
        post {
            try {
                val params = call.receiveParameters()
                val exists = !transaction {
                    Users.select(Users.email eq params["email"]!!).empty()
                }
                if (exists) {
                    call.respond(HttpStatusCode.BadRequest, "User with such email already exists")
                }
                val user = transaction {
                    User.new {
                        email = params["email"]!!
                        passwordHash = params["password"]!!
                    }
                }
                call.sessions.set(UserSession(user.id.value, LocalDateTime.now().plusWeeks(1)))
            } catch (e: NullPointerException) {
                call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
            }
            call.respond(HttpStatusCode.OK, "Sign up successful")
        }
    }

    route("/sign.in") {
        post {
            try {
                val params = call.receiveParameters()
                val user = transaction {
                    User.find(Users.email eq params["email"]!!).first()
                }
                if (user.passwordHash == params["password"]!!) {
                    call.sessions.set(UserSession(user.id.value, LocalDateTime.now().plusWeeks(1)))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Wrong password")
                }
            } catch (e: NoSuchElementException) {
                call.respond(HttpStatusCode.BadRequest, "No such user")
            } catch (e: NullPointerException) {
                call.respond(HttpStatusCode.BadRequest, "Invalid request parameters")
            }
            call.respond(HttpStatusCode.OK, "Sign in successful")
        }
    }

    authenticate("auth-session") {
        route("/test") {
            get {
                call.respond(HttpStatusCode.OK, "You are logged in ${call.principal<UserSession>()!!.userId}")
            }
        }
    }

    route("/logout") {
        get {
            call.sessions.clear<UserSession>()
            call.respond(HttpStatusCode.OK, "Logout successful")
        }
    }
}
