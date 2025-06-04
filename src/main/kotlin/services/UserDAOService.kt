package services

import data.dao.IUserDAO
import model.User
import ui.Console
import ui.Errors
import utils.Encrypting

class UserDAOService(private val userDao: IUserDAO) : IUserDAOService {
    override fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    override fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }

    override fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    override fun deleteUser(UserId: Int) {
        return userDao.deleteUser(UserId)
    }

    override fun insertUser(
        userNombre: String,
        userEmail: String,
        userPassword: String,
        userAccountType: String
    ) {
        return userDao.insertUser(userNombre, userEmail, userPassword, userAccountType)
    }

    override fun updateUser(
        oldId: Int,
        userNombre: String,
        userEmail: String,
        userPassword: String,
        userAccountType: String
    ) {
        return userDao.updateUser(oldId, userNombre, userEmail, userPassword, userAccountType)
    }

}