package app

import model.User
import services.IUserDAOService
import ui.Errors
import ui.IOutput
import utils.Encrypting

class MainActivity(private val userService: IUserDAOService, private val ui: IOutput) {

    fun menu() {
        var exit = false

        try {

            while (!exit) {
                val cyan = "\u001B[36m"
                val reset = "\u001B[0m"

                ui.display(cyan + """
            ╔══════════════════════════════════════╗
            ║                                      ║
            ║         Welcome to ServiceFlow       ║
            ║                                      ║
            ╠══════════════════════════════════════╣
            ║ 1. Log in                            ║
            ║ 2. Register                          ║
            ║ 3. View available services           ║
            ║ 4. Exit                              ║
            ╚══════════════════════════════════════╝
                """.trimIndent() + reset)

                val option = ui.getInput("Select an option »»")?.trim()?.toInt() ?: throw NumberFormatException()

                when (option) {
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    4 -> {
                        ui.display("See you soon! \n\n\n")
                        exit = true
                    }
                    else -> throw Errors.ERROR_UNKNOWN
                }
            }

        } catch (e: NumberFormatException) {
            ui.displayError("Invalid option", "Please select a valid option")
        }
    }



    fun authUser(email: String, password: String): User? {
        return try {
            val user = userService.getUserByEmail(email)

            if (Encrypting.verifyPassword(password, user?.password ?: throw Errors.ERROR_INVALID_EMAIL)) {
                return user
            } else throw Errors.ERROR_INCORRECT_PASSWORD

        } catch (e: Errors.ERROR_INCORRECT_PASSWORD) {
            null
        } catch (e: Errors.ERROR_INVALID_EMAIL) {
            null
        }
    }

}