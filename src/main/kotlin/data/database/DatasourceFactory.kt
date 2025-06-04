package data.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import model.tables.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatasourceFactory {

    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://db.gxzkxzupepdtspqbeqwe.supabase.co:5432/postgres?sslmode=require"
            driverClassName = "org.postgresql.Driver"
            username = "postgres"
            password = "op2uu4fC7BXFptRT"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        val database = HikariDataSource(config)

        Database.connect(database)

        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                UserTable
            )
        }
    }
}