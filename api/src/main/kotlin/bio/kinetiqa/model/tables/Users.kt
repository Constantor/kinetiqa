package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date

object Users : LongIdTable() {
    val email = text("email")
    val passwordHash = text("password_hash")
    val passwordSalt = text("password_salt")
    val googleAuth = text("google_auth")
    val name = text("person_name")
    val dateBirth = date("date_birth")
    val massKg = decimal("mass_kg", 5, 2)
    val heightCm = integer("height_cm")
    val gender = short("gender")
}
