package bio.kinetiqa.model.models

import org.ktorm.entity.Entity
import kotlinx.serialization.*

@Serializable
abstract class Drug : Entity<Drug> {
    companion object : Entity.Factory<Drug>()
    abstract val id: Int
    abstract val labelName: String
    abstract val iupac: String
    abstract val description: String
    abstract val kineticsPlot: String
    abstract val photoURL: String
    abstract val standardDosageMG: Double
    abstract val dosageStepMG: Double
}