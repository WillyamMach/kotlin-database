import java.sql.DriverManager
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class Db {
    private val url = "jdbc:mysql://localhost:3306/db_users"
    private val user = "root"
    private val password = ""
    private var con: Connection? = null

    private fun connection(): Connection? {
        try {
            this.con = DriverManager.getConnection(url, user, password)

        } catch (e: SQLException) {
            e.printStackTrace()
            println("An error has occurred while trying to connect")
        }

        return null
    }

    fun insert(username: String, password: String) {
        this.connection()

        val query = "INSERT INTO tb_users (use_login, use_password) VALUES (?, ?)"
        val insert = this.con?.prepareStatement(query)

        insert?.setString(1, username)
        insert?.setString(2, password)
        insert?.executeUpdate()

        con?.close()
    }

    fun select() {
        this.connection()

        val query = "SELECT * FROM tb_users"
        val result = this.con?.createStatement()?.executeQuery(query)
        while(result?.next() == true) {
            println("${result.getInt("use_id")}, ${result.getString("use_login")}, ${result.getString("use_password")}")
        }

        con?.close()
    }

    fun update(username: String, password: String, id: Int) {
        this.connection()

        val query = "UPDATE tb_users SET use_login = ?, use_password = ? WHERE use_id = ?"
        val statement: PreparedStatement? = con?.prepareStatement(query)

        statement?.setString(1, username)
        statement?.setString(2, password)
        statement?.setInt(3, id)
        statement?.executeUpdate()

        con?.close()
    }

    fun delete(id: Int) {
        this.connection()

        val query = "DELETE FROM tb_users WHERE use_id = ?"
        val statement = con?.prepareStatement(query)

        statement?.setInt(1, id)
        statement?.executeUpdate()

        con?.close()
    }
}

