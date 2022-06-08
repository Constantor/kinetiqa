package bio.kinetiqa

import bio.kinetiqa.core.DatabaseFactory
import bio.kinetiqa.model.entities.User
import bio.kinetiqa.model.sessions.UserSession
import bio.kinetiqa.model.tables.Users
import io.ktor.server.application.*
import bio.kinetiqa.plugins.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

fun main(args: Array<String>): Unit =
	io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
	configureRouting()
	configureSerialization()
	configureHTTP()
	configureDoubleReceive()
	install(Sessions) {
		val secretSignKey = hex("6819b57a326945c1968f45236589")
		cookie<UserSession>("user_id_session", SessionStorageMemory()) {
			cookie.path = "/"
			//cookie.maxAgeInSeconds = 60
			//cookie.secure = true
			transform(SessionTransportTransformerMessageAuthentication(secretSignKey))
		}
	}
	install(Authentication) {
		session<UserSession>("auth-session") {
			validate { session ->
				session
			}
		}
	}

	DatabaseFactory.init()
}
