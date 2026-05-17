<%@ page import="java.sql.*" %>
<%@ page import="dao.DBConnection" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

<style>

body{
    font-family: Arial;
    margin: 40px;
}

.container{
    width: 70%;
    margin: auto;
}

input{
    width: 100%;
    padding: 10px;
    margin-top: 10px;
}

button{
    padding: 10px;
    margin-top: 10px;
}

table{
    width: 100%;
    border-collapse: collapse;
    margin-top: 30px;
}

table, th, td{
    border: 1px solid black;
}

th, td{
    padding: 10px;
    text-align: center;
}

</style>

</head>

<body>

<div class="container">

<h2>Welcome ${username}</h2>

<h3>Add Student</h3>

<form action="addStudent" method="post">

<input type="text" name="name" placeholder="Enter Name" required>

<input type="text" name="course" placeholder="Enter Course" required>

<input type="text" name="fee" placeholder="Enter Fee" required>

<button type="submit">Add Student</button>

</form>

<h3>Student Records</h3>

<table>

<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Course</th>
    <th>Fee</th>
    <th>Edit</th>
    <th>Delete</th>
</tr>

<%

try{

    Connection con = DBConnection.getConnection();

    String query = "select * from students";

    PreparedStatement ps = con.prepareStatement(query);

    ResultSet rs = ps.executeQuery();

    int serialNumber = 1;

    while(rs.next()){

%>

<tr>

<td><%= serialNumber++ %></td>

<td><%= rs.getString("name") %></td>

<td><%= rs.getString("course") %></td>

<td><%= rs.getDouble("fee") %></td>

<td>

<a href="editStudent.jsp?id=<%= rs.getInt("id") %>">
Edit
</a>

</td>

<td>

<a href="deleteStudent?id=<%= rs.getInt("id") %>">
Delete
</a>

</td>

</tr>

<%

    }

}catch(Exception e){
    e.printStackTrace();
}

%>

</table>

</div>

</body>
</html>