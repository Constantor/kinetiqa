package bio.kinetiqa.core

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.slf4j.LoggerFactory
import javax.sql.DataSource

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert

object DatabaseFactory {

	fun init() {
		Database.connect("jdbc:postgresql://apiuser:AVNS_TjDHRPxTKdDvbj4@kinetiqa-postgresql-db-do-user-2264611-0.b.db.ondigitalocean.com:25060/kinetiqa?sslmode=require", "org.postgresql.Driver")
		println()
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
