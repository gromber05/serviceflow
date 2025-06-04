package ui

class Console: IOutput {
    override fun display(message: String) {
        println(message)
    }

    override fun displayError(name: String, message: String) {
        println("ERROR [$name]: $message")
    }

    override fun clear() {
        repeat(50) { println() }
    }

    override fun getInput(prompt: String): String? {
        display(prompt)
        return readlnOrNull()
    }

    override fun getPassword(prompt: String): String? {
        val console = System.console()
        return if (console != null) {
            val passwordChars = console.readPassword(prompt)
            String(passwordChars)
        } else {
            displayError(Errors.ERROR_PASSWORD.toString(), Errors.ERROR_PASSWORD.description)
            null
        }
    }



}