package bio.kinetiqa.model.tables

import bio.kinetiqa.model.models.Drug
import org.ktorm.schema.*

object Drugs : Table<Drug>("drugs") {
	val id = int("id").primaryKey().bindTo { it.id }
	val labelName = varchar("label_name").bindTo { it.labelName }
	val iupac = varchar("iupac").bindTo { it.iupac }
	val description = varchar("description").bindTo { it.description }
	val kineticsPlot = varchar("kinetics_plot").bindTo { it.kineticsPlot }
	val photoURL = varchar("photo_url").bindTo { it.photoURL }
	val standardDosageMG = double("standard_dosage_mg").bindTo { it.standardDosageMG }
	val dosageStepMG = double("dosage_step_mg").bindTo { it.dosageStepMG }
}