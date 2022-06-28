package bio.kinetiqa.android.model.db.entites

import java.math.BigDecimal

data class Drug(
    val id: Int,
    val labelName: String,
    val iupac: String,
    val description: String,
    val kineticsPlot: String,
    val photoURL: String,
    val standardDosageMG: BigDecimal,
    val dosageStepMG: BigDecimal
)