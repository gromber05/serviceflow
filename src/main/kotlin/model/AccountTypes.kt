package model

enum class AccountTypes {
    MASTER, ADMIN, DEFAULT;
    
    override fun toString(): String {
        return name.lowercase()
    }

    fun getAccountType(): AccountTypes? {
        return AccountTypes.valueOf(this.toString().uppercase())
    }
    
}