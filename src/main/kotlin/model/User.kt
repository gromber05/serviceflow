package model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val accountType: String = AccountTypes.DEFAULT.toString()
) {
    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email', password='$password', accountType=$accountType)"
    }

    override fun hashCode(): Int {
        return id
    }
}