import app.MainActivity
import data.dao.UserDAO
import data.database.DatasourceFactory
import services.UserDAOService
import ui.Console

fun main() {
    DatasourceFactory.init()

    val userDao = UserDAO()

    val userService = UserDAOService(userDao)
    val console = Console()

    val app = MainActivity(userService,console)
    app.menu()

}