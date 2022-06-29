package bio.kinetiqa.android.model.db.entites

import java.time.Instant

data class Intake(
    val id: Long,
    val userId: Long,
    val drugId: Int,
    val massIntookMg: Int,
    val timeWhen: Instant
)
