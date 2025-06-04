package ui

interface IOutput{
    fun display(message: String)
    fun displayError(name: String, message: String)
    fun clear()
    fun getInput(prompt: String): String?
    fun getPassword(prompt: String = "Password: "): String?
}