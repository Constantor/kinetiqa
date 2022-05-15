package bio.kinetiqa.core

import org.jetbrains.exposed.sql.*

object DatabaseFactory {
	fun init() {
		val user: String = System.getenv("DB_USER").takeUnless { it.isNullOrEmpty() } ?: "doadmin"
		val pass: String = System.getenv("DB_PASS").takeUnless { it.isNullOrEmpty() } ?: "AVNS_Xwx137YDBXY3dXL"
		val host: String = System.getenv("DB_HOST").takeUnless { it.isNullOrEmpty() } ?: "kinetiqa-postgresql-db-do-user-2264611-0.b.db.ondigitalocean.com"
		val port: Int = 25060
		val database: String = "kinetiqa"

		Database.connect("jdbc:postgresql://${host}:${port}/${database}?ssl=true&sslmode=require", driver = "org.postgresql.Driver", user = user, password = pass)
	}
}
