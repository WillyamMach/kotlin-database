import java.sql.DriverManager
import java.sql.Connection
import java.sql.SQLException

class Db {
    private val url = "jdbc:mysql://localhost:3306/db_users"
    private val user = "root"
    private val password = ""
    private var con: Connection? = null

    fun connection(): Connection? {
        try {
            this.con = DriverManager.getConnection(url, user, password)
            print("successfully connected")

        } catch (e: SQLException) {
            e.printStackTrace()
            println("An error has occurred while trying to connect")
        }

        return null
    }

    fun insert(username: String, password: String) {
        this.connection()

        var query = "INSERT INTO tb_users (use_login, use_password) VALUES (?, ?)"

        var insert = this.con?.prepareStatement(query)

        insert?.setString(1, username)
        insert?.setString(2, password)

        insert?.executeUpdate()
    }
}

