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
import java.time.LocalDateTime

fun main(args: Array<String>): Unit =
	io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
	configureRouting()
	configureSerialization()
	configureHTTP()
	configureDoubleReceive()
	install(Sessions) {
		val secretEncryptKey = hex("00112233445566778899aabbccddeeff")
		val secretSignKey = hex("6819b57a326945c1968f45236589")
		header<UserSession>("user_session") {
			transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
		}
	}
	install(Authentication) {
		session<UserSession>("auth-session") {
			validate { session ->
				if(LocalDateTime.parse(session.expirationDate).isAfter(LocalDateTime.now())) {
					session
				} else {
					null
				}
			}
		}
	}

	DatabaseFactory.init()
}
