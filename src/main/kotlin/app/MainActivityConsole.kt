package app

import model.User
import services.IUserDAOService
import ui.Errors
import ui.IOutput
import utils.Encrypting
import java.sql.Time

class MainActivityConsole(private val userService: IUserDAOService, private val ui: IOutput) {

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

                val option = ui.getInput("Select an option »»")?.trim()?.toInt() ?: ui.displayError("Invalid option", "Please select a valid option")

                when (option) {
                    1 -> {
                        try {
                            ui.clear()
                            val email = ui.getInput("Enter your email »» ")

                            ui.clear()
                            val password = ui.getPassword("Enter your password »» ")

                            if (authUser(email ?: throw Errors.ERROR_UNKNOWN, password ?: throw Errors.ERROR_UNKNOWN) != null) {
                                ui.display("Logged in successfully!")
                                Thread.sleep(4000)
                            } else throw Errors.ERROR_INCORRECT_PASSWORD

                        } catch (e: Errors.ERROR_EMPTY_EMAIL) {
                            ui.displayError("Incorrect email", "Please try again")
                        } catch (e: Errors.ERROR_EMPTY_PASSWORD) {
                            ui.displayError("Incorrect password", "Please try again")
                        } catch (e: Errors.ERROR_INCORRECT_PASSWORD) {
                            ui.displayError("Incorrect password", "Please try again")
                        }
                    }
                    2 -> {
                        try {
                            ui.clear()
                            val username = ui.getInput("Enter your name »» ")

                            ui.clear()
                            val email = ui.getInput("Enter your email »» ")

                            ui.clear()
                            val password = ui.getPassword("Enter your password »» ")

                            ui.clear()
                            val confirm = ui.getPassword("Confirm your password »» ")

                            if (password != confirm) throw Errors.ERROR_PASSWORD else registerUser(username ?: throw Errors.ERROR_UNKNOWN, email ?: throw Errors.ERROR_UNKNOWN, password ?: throw Errors.ERROR_UNKNOWN)

                            ui.clear()
                            ui.display("Account created successfully!")
                            Thread.sleep(4000)

                        } catch (e: Errors.ERROR_EMPTY_NAME) {
                            ui.displayError("Invalid name", "Name cannot be empty")
                        }
                    }
                    3 -> {

                    }
                    4 -> {
                        ui.display("See you soon! \n\n\n")
                        exit = true
                    }
                    else -> throw Errors.ERROR_UNKNOWN
                }
                ui.clear()
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

    fun registerUser(username: String, email: String, password: String) {
        userService.insertUser(username, email, password, "DEFAULT")
    }

}