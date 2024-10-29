package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.DBUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/api/read"})
public class ApiRead extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtils.connect();
        showJsonRecord(resp);
    }

    public static void showJsonRecord(HttpServletResponse resp) throws IOException {
        ResultSet resultSet = null;
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        JSONArray jsonArray = new JSONArray();

        try {
            resultSet = DBUtils.selectAll("emp");

            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", resultSet.getInt("id"));
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("age", resultSet.getInt("age"));

                jsonArray.add(jsonObject);
            }

            writer.print(jsonArray.toString());
            writer.flush();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}