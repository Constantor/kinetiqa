package bio.kinetiqa.model.sessions

import io.ktor.server.auth.*

data class UserSession(val id: Long) : Principal
