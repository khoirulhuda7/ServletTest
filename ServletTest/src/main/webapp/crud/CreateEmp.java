package crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/CreateEmp"})
public class CreateEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'></head>");
        writer.println("<body class='container'>");
        writer.println("<h2 class='text-center'>Input Data Karyawan</h2>");
        writer.println("<form action='CreateEmp' method='post' class='form-horizontal'>");
        writer.println("<div class='form-group row'>");
        writer.println("<label for='id' class='col-sm-2 col-form-label'> ID </label>");
        writer.println("<div class='col-sm-10'>");
        writer.println("<input type='text' name='id' class='form-control' required/>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("<div class='form-group row'>");
        writer.println("<label for='nama' class='col-sm-2 col-form-label'> Nama </label>");
        writer.println("<div class='col-sm-10'>");
        writer.println("<input type='text' name='nama' class='form-control' required/>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("<div class='form-group row'>");
        writer.println("<label for='age' class='col-sm-2 col-form-label'> Age </label>");
        writer.println("<div class='col-sm-10'>");
        writer.println("<input type='text' name='age' class='form-control' required/>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("<div class='form-group row'>");
        writer.println("<div class='col-sm-10 offset-sm-2'>");
        writer.println("<input type='submit' class='btn btn-primary' value='Submit'/>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        int id = Integer.parseInt(stringId);
        String stringNama = req.getParameter("nama");
        String stringAge = req.getParameter("age");
        int age = Integer.parseInt(stringAge);

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'></head>");
        writer.println("<body class='container'>");

        DBUtils.connect();
        try {
            if (DBUtils.insert("emp", id, stringNama, age)) {
                writer.println("<div class='alert alert-success'>Insert " + stringNama + " berhasil!</div>");
            } else {
                writer.println("<div class='alert alert-danger'>Insert " + stringNama + " gagal!</div>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        writer.println("<h4><a class='btn btn-secondary' href='ReadEmp'>Kembali ke menu utama</a></h4>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }
}