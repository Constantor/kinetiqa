package bio.kinetiqa.model.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Column

object Drugs : Table() {
	val id: Column<Int> = integer("id").autoIncrement()
	val label_name: Column<String> = text("label_name").uniqueIndex()
	val iupac: Column<String> = text("iupac")
	val description: Column<String> = text("description")
	val kinetics_plot: Column<String> = text("kinetics_plot")
	val photo_url: Column<String> = text("photo_url")
	val standard_dosage_mg: Column<Double> = double("standard_dosage_mg")
	val dosage_step_mg: Column<Double> = double("dosage_step_mg")
	override val primaryKey = PrimaryKey(id, name = "id")
}
