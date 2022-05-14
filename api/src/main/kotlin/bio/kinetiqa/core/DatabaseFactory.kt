package bio.kinetiqa.core

import org.jetbrains.exposed.sql.*

object DatabaseFactory {
	fun init() {
		Database.connect("jdbc:postgresql://kinetiqa-postgresql-db-do-user-2264611-0.b.db.ondigitalocean.com:25060/kinetiqa", driver = "org.postgresql.Driver", user = "doadmin", password = "AVNS_Xwx137YDBXY3dXL") // ?sslmode=require
	}
}
