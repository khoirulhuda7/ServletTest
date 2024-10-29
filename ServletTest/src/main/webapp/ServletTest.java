import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(asyncSupported = false, name = "HelloServlet1", urlPatterns = {"/h"})
public class ServletTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // tangkap parameter get
        String pekerjaan = req.getParameter("Pekerjaan");
        //   menampilkan html
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//        out.write("<h2>Hello "+ nama + " </h2>");
        //menampilkan json
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.write("{\"method\":\"GET\",\"message\":\"" + pekerjaan + "\"}");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String pekerjaan = req.getParameter("pekerjaan");
        //menampilkan json
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.write("{\"method\":\"POST\",\"message\":\"Pekerjaan: " + pekerjaan + "\"}");
        out.close();
    }
}