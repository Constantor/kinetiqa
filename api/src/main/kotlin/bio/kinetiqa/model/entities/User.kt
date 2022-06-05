package bio.kinetiqa.model.entities

import bio.kinetiqa.model.tables.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(Users)

    val email by Users.email
    val passwordHash by Users.passwordHash
    val passwordSalt by Users.passwordSalt
    val googleAuth by Users.googleAuth
    val name by Users.name
    val dateBirth by Users.dateBirth
    val massKg by Users.massKg
    val heightCm by Users.heightCm
    val gender by Users.gender
}