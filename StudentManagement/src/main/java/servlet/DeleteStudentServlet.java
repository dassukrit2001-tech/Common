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

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(request.getParameter("id"));

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "delete from students where id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}