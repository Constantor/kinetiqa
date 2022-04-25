package bio.kinetiqa.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val email: String, val password: String, val name: String)
