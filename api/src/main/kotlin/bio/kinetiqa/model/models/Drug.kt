package bio.kinetiqa.model.models

import org.ktorm.entity.Entity

interface Drug : Entity<Drug> {
    companion object : Entity.Factory<Drug>()
    val id: Int
    val labelName: String
    val iupac: String
    val description: String
    val kineticsPlot: String
    val photoURL: String
    val standardDosageMG: Double
    val dosageStepMG: Double
}