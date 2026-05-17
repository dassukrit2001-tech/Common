package servlet;

import dao.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String course = request.getParameter("course");

        double fee =
                Double.parseDouble(request.getParameter("fee"));

        try {

            Connection con = DBConnection.getConnection();

            String query =
              "insert into students(name,course,fee) values(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, fee);

            ps.executeUpdate();

            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}