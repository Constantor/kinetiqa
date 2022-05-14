package bio.kinetiqa.core

import bio.kinetiqa.models.Drugs
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.slf4j.LoggerFactory

import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create

object DatabaseFactory {
	fun init() {
		Database.connect("jdbc:postgresql://kinetiqa-postgresql-db-do-user-2264611-0.b.db.ondigitalocean.com:25060/kinetiqa", driver = "org.postgresql.Driver", user = "doadmin", password = "AVNS_Xwx137YDBXY3dXL") // ?sslmode=require
		/*Database.connect(hikari())
		transaction {
			create(Users)
			Users.insert {
				it[name] = "Ishrat Khan"
				it[registerDate] = System.currentTimeMillis()
			}
			Users.insert {
				it[name] = "Suhaib Roomy"
				it[registerDate] = System.currentTimeMillis()
			}
		}*/
	}

	/*private fun hikari(): HikariDataSource {
		val config = HikariConfig()
		config.driverClassName = "org.h2.Driver"
		config.jdbcUrl = "jdbc:h2:mem:test"
		config.maximumPoolSize = 3
		config.isAutoCommit = false
		config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
		config.validate()
		return HikariDataSource(config)
	}*/
}
