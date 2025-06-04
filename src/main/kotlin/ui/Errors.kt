package ui

sealed class Errors(val description: String) : Exception() {
    object ERROR_EMPTY_NAME : Errors("Name cannot be empty")
    object ERROR_EMPTY_EMAIL : Errors("Email cannot be empty")
    object ERROR_EMPTY_PASSWORD : Errors("Password cannot be empty")
    object ERROR_EMPTY_ACCOUNT_TYPE : Errors("Account type cannot be empty")
    object ERROR_INVALID_ACCOUNT_TYPE : Errors("Invalid account type")
    object ERROR_PASSWORD : Errors("Unknown password error")
    object ERROR_INVALID_EMAIL : Errors("Invalid email")
    object ERROR_UNKNOWN : Errors("Unknown error")
    object ERROR_INCORRECT_PASSWORD : Errors("Incorrect password")
}