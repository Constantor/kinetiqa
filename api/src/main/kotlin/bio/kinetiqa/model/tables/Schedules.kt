package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.time
import org.jetbrains.exposed.sql.javatime.timestamp

object Schedules : LongIdTable() {
    val userId = reference("user_id", Users)
    val drugId = reference("drug_id", Drugs)
    val comment = text("comment")
    val whenTaken = timestamp("when_taken")
    val dosageMg = decimal("dosage_mg", 9, 5)
    val weekDay = short("week_day")
    val timeOfDay = time("time_of_day")
}
