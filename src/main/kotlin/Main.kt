import app.MainActivityConsole
import data.dao.UserDAO
import data.database.DatasourceFactory
import services.UserDAOService
import ui.Console
import kotlin.system.exitProcess

fun main() {

    val console = Console()

    try {
        DatasourceFactory.init(DatasourceFactory.Mode.DEBUG)
    } catch (e: Exception) {
        console.displayError("Database connection error", e.message ?: "Unknown error")
        exitProcess(45)
    }

    val userDao = UserDAO()

    val userService = UserDAOService(userDao)


    val app = MainActivityConsole(userService,console)
    app.menu()

}