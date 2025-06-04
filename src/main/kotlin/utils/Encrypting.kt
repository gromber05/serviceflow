package utils

import at.favre.lib.crypto.bcrypt.BCrypt

object Encrypting {

    fun encrypt(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    fun verifyPassword(password: String, hash: String): Boolean {
        val result = BCrypt.verifyer().verify(password.toCharArray(), hash)
        return result.verified
    }

}