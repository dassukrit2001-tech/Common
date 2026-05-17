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

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(request.getParameter("id"));

        String name =
                request.getParameter("name");

        String course =
                request.getParameter("course");

        double fee =
                Double.parseDouble(request.getParameter("fee"));

        try {

            Connection con = DBConnection.getConnection();

            String query =
            "update students set name=?, course=?, fee=? where id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, fee);
            ps.setInt(4, id);

            ps.executeUpdate();

            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}