package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import utils.DBUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/update"})
public class ApiUpdate extends HttpServlet {
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set content type
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        try {
            // Retrieve data from the request body
            String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

            // Parse the JSON string using Simple JSON
            JSONObject jsonObject = (JSONObject) JSONValue.parse(body);

            int id = ((Long) jsonObject.get("id")).intValue(); // Mengonversi Long ke int
            String name = (String) jsonObject.get("name");
            int age = ((Long) jsonObject.get("age")).intValue(); // Mengonversi Long ke int

            // Connect to the database and update the record
            DBUtils.connect();
            boolean isUpdated = DBUtils.update("emp", id, name, age);

            // Respond with success or failure
            if (isUpdated) {
                writer.write("{\"status\": \"success\", \"message\": \"Record updated successfully!\"}");
            } else {
                writer.write("{\"status\": \"failure\", \"message\": \"Failed to update record.\"}");
            }
        } catch (Exception e) {
            // Handle any errors
            writer.write("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        } finally {
            writer.flush();
        }
    }
}