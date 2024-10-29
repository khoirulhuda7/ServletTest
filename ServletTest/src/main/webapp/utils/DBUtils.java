package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    private static Connection conn;

    // Method untuk menghubungkan ke database
    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root", "");
            System.out.println("Connection berhasil");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi Gagal! " + e.getMessage());
        }
    }

    // Method untuk menampilkan semua record dari tabel 'emp'
    public static ResultSet selectAll(String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM " + tableName);
    }

    // Method untuk menambahkan record ke tabel 'emp'
    public static boolean insert(String tableName, int id, String name, int age) throws SQLException {
        String sqlInsert = "INSERT INTO " + tableName + " (id, name, age) VALUES (" + id + ", '" + name + "', " + age + ")";
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sqlInsert) > 0;
    }

    // Method untuk mengupdate record di tabel 'emp'
    public static boolean update(String tableName, int id, String name, int age) throws SQLException {
        String sqlUpdate = "UPDATE " + tableName + " SET name='" + name + "', age=" + age + " WHERE id=" + id;
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sqlUpdate) > 0;
    }

    // Method untuk menghapus record dari tabel 'emp'
    public static boolean delete(String tableName, int id) throws SQLException {
        String sqlDelete = "DELETE FROM " + tableName + " WHERE id=" + id;
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sqlDelete) > 0;
    }
}