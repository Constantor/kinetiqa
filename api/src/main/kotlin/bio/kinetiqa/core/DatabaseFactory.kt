package bio.kinetiqa.core

import org.jetbrains.exposed.sql.*

object DatabaseFactory {
	fun init() {
		Database.connect("jdbc:postgresql://private-kinetiqa-postgresql-db-do-user-2264611-0.b.db.ondigitalocean.com:25060/kinetiqa?ssl=true&sslmode=require", driver = "org.postgresql.Driver", user = "apiuser", password = "AVNS_TjDHRPxTKdDvbj4")
	}
}
