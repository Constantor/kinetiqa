package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object Courses : LongIdTable() {
    val userId = reference("user_id", Users)
    val drugId = reference("drug_id", Drugs)
    val whenAdded = timestamp("when_added")
}