package model.tables

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 255)
    val accountType = varchar("account_type", 10)

    override val primaryKey = PrimaryKey(id)
}