package bio.kinetiqa.model.dataclasses

import kotlinx.serialization.*

@Serializable
data class Drug(val id: Int, val labelName: String, val iupac: String, val description: String, val kineticsPlot: String, val photoURL: String, val standardDosageMG: Double, val dosageStepMG: Double)
