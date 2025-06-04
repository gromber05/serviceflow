package data.dao

import model.User
import model.tables.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAO() : IUserDAO {

    override fun getAllUsers(): List<User> {
        return transaction {
            UserTable.selectAll().map {
                User(
                    it[UserTable.id],
                    it[UserTable.name],
                    it[UserTable.email],
                    it[UserTable.password],
                    it[UserTable.accountType]
                )
            }
        }
    }

    override fun getUserById(id: Int): User? {
        return transaction {
            UserTable.select { UserTable.id eq id }.map {
                User(
                    it[UserTable.id],
                    it[UserTable.name],
                    it[UserTable.email],
                    it[UserTable.password],
                    it[UserTable.accountType]
                )
            }.firstOrNull()
        }
    }

    override fun getUserByEmail(email: String): User? {
        return transaction {
            UserTable.select { UserTable.email eq email }.map {
                User(
                    it[UserTable.id],
                    it[UserTable.name],
                    it[UserTable.email],
                    it[UserTable.password],
                    it[UserTable.accountType]
                )
            }.firstOrNull()
        }
    }

    override fun insertUser(userNombre: String, userEmail: String, userPassword: String, userAccountType: String) {
        transaction {
            UserTable.insert {
                it[name] = userNombre
                it[email] = userEmail
                it[password] = userPassword
                it[accountType] = userAccountType
            }
        }
    }

    override fun updateUser(oldId: Int, userNombre: String, userEmail: String, userPassword: String, userAccountType: String) {
        transaction {
            UserTable.update({ UserTable.id eq oldId }) {
                it[name] = userNombre
                it[email] = userEmail
                it[password] = userPassword
                it[accountType] = userAccountType
            }
        }
    }

    override fun deleteUser(UserId: Int) {
        transaction {
            UserTable.deleteWhere { UserTable.id eq UserId }
        }
    }
}