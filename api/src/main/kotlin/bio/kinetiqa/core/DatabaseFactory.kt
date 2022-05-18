package bio.kinetiqa.core

import org.jetbrains.exposed.sql.*
import kotlin.Exception;

object DatabaseFactory {
	fun init() {
		val user: String? = System.getenv("DB_USER").takeUnless { it.isNullOrEmpty() }
		val pass: String? = System.getenv("DB_PASS").takeUnless { it.isNullOrEmpty() }
		if(user.isNullOrEmpty() || pass.isNullOrEmpty())
			throw Exception("Missing DB auth credentials")
		val host: String = System.getenv("DB_HOST").takeUnless { it.isNullOrEmpty() }
		if(host.isNullOrEmpty())
			throw Exception("Missing DB host")
		val port: Int = 25060
		val database: String = "kinetiqa"

		Database.connect("jdbc:postgresql://${host}:${port}/${database}?ssl=true&sslmode=require", driver = "org.postgresql.Driver", user = user, password = pass)
	}
}
