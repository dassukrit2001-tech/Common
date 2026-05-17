package servlet;

import dao.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewStudents")
public class ViewStudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("select * from students");

            ResultSet rs = ps.executeQuery();

            out.println("<h2>Student List</h2>");

            out.println("<table border='1'>");

            out.println(
              "<tr><th>ID</th><th>Name</th><th>Course</th><th>Fee</th><th>Edit</th><th>Delete</th></tr>");

            while(rs.next()) {

                out.println("<tr>");

                out.println("<td>"+rs.getInt("id")+"</td>");
                out.println("<td>"+rs.getString("name")+"</td>");
                out.println("<td>"+rs.getString("course")+"</td>");
                out.println("<td>"+rs.getDouble("fee")+"</td>");

                out.println(
                  "<td><a href='editStudent.jsp?id="
                  +rs.getInt("id")+"'>Edit</a></td>");

                out.println(
                  "<td><a href='deleteStudent?id="
                  +rs.getInt("id")+"'>Delete</a></td>");

                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}