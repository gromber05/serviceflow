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
            passwordChars?.let { String(it) }
        } else {
            display("⚠️ Console not available. The password will not be hidden.")
            display(prompt)
            readlnOrNull()
        }
    }





}