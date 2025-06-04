package data.dao

import model.User

interface IUserDAO {
    fun getAllUsers() : List<User>
    fun getUserById(id: Int) : User?
    fun getUserByEmail(email: String): User?
    fun deleteUser(UserId: Int)
    fun insertUser(userNombre: String, userEmail: String, userPassword: String, userAccountType: String)
    fun updateUser(oldId: Int, userNombre: String, userEmail: String, userPassword: String, userAccountType: String)
}