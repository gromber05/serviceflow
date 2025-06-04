package data.database

import model.tables.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatasourceFactory {

    fun init() {
        Database.connect(
            url = "jdbc:postgresql://db.gxzkxzupepdtspqbeqwe.supabase.co:5432/postgres?user=postgres&password=op2uu4fC7BXFptRT",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "op2uu4fC7BXFptRT"
        )

        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                UserTable
            )
        }
    }
}