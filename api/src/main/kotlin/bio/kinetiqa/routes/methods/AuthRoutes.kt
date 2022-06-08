package bio.kinetiqa.routes.methods

import io.ktor.server.application.*
import io.ktor.server.routing.*
import bio.kinetiqa.core.utils.Params
import bio.kinetiqa.model.entities.User
import bio.kinetiqa.model.sessions.UserSession
import bio.kinetiqa.model.tables.Users
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.authRouting() {
    route("/sign.up") {
        post {
            val params = call.receiveParameters()
            val user = transaction {
                User.new {
                    email = params["email"]!!
                    passwordHash = params["password"]!!
                }
            }
            call.sessions.set(UserSession(user.id.value))
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
                    call.sessions.set(UserSession(user.id.value))
                } else {
                    call.respond(HttpStatusCode.OK, "Wrong password")
                }
            } catch (e: NoSuchElementException) {
                call.respond(HttpStatusCode.OK, "No such user")
            }
            call.respond(HttpStatusCode.OK, "Sign in successful")
        }
    }

    authenticate("auth-session") {
        route("/test") {
            get {
                call.respond(HttpStatusCode.OK, "You are logged in ${call.principal<UserSession>()!!.id}")
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
