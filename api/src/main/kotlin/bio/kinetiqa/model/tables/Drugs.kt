package bio.kinetiqa.model.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Drugs : IntIdTable() {
    val labelName = text("label_name").uniqueIndex()
    val iupac = text("iupac")
    val description = text("description")
    val kineticsPlot = text("kinetics_plot")
    val photoURL = text("photo_url")
    val standardDosageMG = decimal("standard_dosage_mg", 9, 5)
    val dosageStepMG = decimal("dosage_step_mg", 9, 5)
}
