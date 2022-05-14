package bio.kinetiqa.model.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Column

object Drugs : Table() {
	val id: Column<Int> = integer("id").autoIncrement()
	val labelName: Column<String> = text("label_name").uniqueIndex()
	val iupac: Column<String> = text("iupac")
	val description: Column<String> = text("description")
	val kineticsPlot: Column<String> = text("kinetics_plot")
	val photoURL: Column<String> = text("photo_url")
	val standardDosageMG: Column<Double> = double("standard_dosage_mg")
	val dosageStepMG: Column<Double> = double("dosage_step_mg")
	override val primaryKey = PrimaryKey(id, name = "id")
}
