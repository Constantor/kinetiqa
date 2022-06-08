package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

object Users : LongIdTable() {
    val email = text("email").uniqueIndex()
    val passwordHash = text("password_hash").clientDefault { "" }
    val passwordSalt = text("password_salt").clientDefault { "" }
    val googleAuth = text("google_auth").clientDefault { "" }
    val name = text("person_name").clientDefault { "" }
    val dateBirth = date("date_birth").clientDefault { LocalDate.now() }
    val massKg = decimal("mass_kg", 5, 2).clientDefault { BigDecimal(0) }
    val heightCm = integer("height_cm").clientDefault { 0 }
    val gender = short("gender").clientDefault { 0 }
}
