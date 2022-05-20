package bio.kinetiqa.core

import org.ktorm.database.Database
import kotlin.Exception

object DatabaseFactory {

	lateinit var database: Database

	fun init() {
		val user: String? = System.getenv("DB_USER").takeUnless { it.isNullOrEmpty() }
		val pass: String? = System.getenv("DB_PASS").takeUnless { it.isNullOrEmpty() }
		if(user.isNullOrEmpty() || pass.isNullOrEmpty())
			throw Exception("Missing DB auth credentials")
		val host: String? = System.getenv("DB_HOST").takeUnless { it.isNullOrEmpty() }
		if(host.isNullOrEmpty())
			throw Exception("Missing DB host")
		val port: Int = 25060
		val databaseName: String = "kinetiqa"

		database = Database.connect("jdbc:postgresql://${host}:${port}/${databaseName}?ssl=true&sslmode=require", driver = "org.postgresql.Driver", user = user, password = pass)
	}
}
