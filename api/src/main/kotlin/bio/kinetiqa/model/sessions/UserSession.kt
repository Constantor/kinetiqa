package bio.kinetiqa.model.sessions

import io.ktor.server.auth.*
import java.time.LocalDateTime

data class UserSession(val userId: Long, val expirationDate: LocalDateTime) : Principal
