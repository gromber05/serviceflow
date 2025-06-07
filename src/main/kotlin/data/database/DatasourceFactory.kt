package data.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import model.tables.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatasourceFactory {

    enum class Mode {
        DEBUG, DEFAULT
    }

    fun init(mode: Mode = Mode.DEFAULT) {
        var config: HikariConfig

        when (mode) {
            Mode.DEBUG -> {
                config = HikariConfig().apply {
                    jdbcUrl = "jdbc:h2:./data/dbtest"
                    driverClassName = "org.h2.Driver"
                    username = "sa"
                    password = ""
                    maximumPoolSize = 3
                    isAutoCommit = false
                    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                    validate()
                }
            }
            Mode.DEFAULT -> {
                config = HikariConfig().apply {
                    jdbcUrl = "jdbc:postgresql://db.gxzkxzupepdtspqbeqwe.supabase.co:5432/postgres?sslmode=require"
                    driverClassName = "org.postgresql.Driver"
                    username = "postgres"
                    password = "op2uu4fC7BXFptRT"
                    maximumPoolSize = 3
                    isAutoCommit = false
                    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                    validate()
                }
            }
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