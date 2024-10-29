package crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http .HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/ReadEmp"})
public class ReadEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtils.connect();
        showHtmlRecord(resp);
    }

    public static void showHtmlRecord(HttpServletResponse resp) throws IOException {
        ResultSet resultSet = null;
        PrintWriter writer = resp.getWriter();
        try {
            resultSet = DBUtils.selectAll("emp");
            writer.println("<html>");
            writer.println("<head><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'></head>");
            writer.println("<body class='container'>");
            writer.println("<h2 class='text-center'>Data Karyawan</h2>");
            writer.println("<table class='table table-striped table-bordered'>");
            writer.println("<thead class='thead-dark'>");
            writer.println("<tr>");
            writer.println("<th scope='col'>ID</th>");
            writer.println("<th scope='col'>Nama</th>");
            writer.println("<th scope='col'>Age</th>");
            writer.println("<th scope='col' colspan='2'>Action</th>");
            writer.println("</tr>");
            writer.println("</thead>");
            writer.println("<tbody>");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("name");
                int age = resultSet.getInt("age");
                writer.println("<tr>");
                writer.println("<td>" + id + "</td>");
                writer.println("<td>" + nama + "</td>");
                writer.println("<td>" + age + "</td>");
                writer.println("<td><a class='btn btn-warning' href='EditEmp?id=" + id + "&nama=" + nama + "&age=" + age + "'> Edit </a></td>");
                writer.println("<td><a class='btn btn-danger' href='DeleteEmp?id=" + id + "&nama=" + nama + "&age=" + age + "'> Delete </a></td>");
                writer.println("</tr>");
            }
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("<a class='btn btn-primary' href='CreateEmp'>Add Karyawan</a>");
            writer.println("</body>");
            writer.println("</html>");
            writer.flush();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}