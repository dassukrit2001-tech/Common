<%@ page import="java.sql.*" %>
<%@ page import="dao.DBConnection" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>

<style>

body{
    font-family: Arial;
    margin: 40px;
}

.container{
    width: 40%;
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

</style>

</head>

<body>

<div class="container">

<h2>Edit Student</h2>

<%

int id = Integer.parseInt(request.getParameter("id"));

Connection con = DBConnection.getConnection();

String query = "select * from students where id=?";

PreparedStatement ps = con.prepareStatement(query);

ps.setInt(1, id);

ResultSet rs = ps.executeQuery();

if(rs.next()){

%>

<form action="updateStudent" method="post">

<input type="hidden" name="id"
value="<%= rs.getInt("id") %>">

<input type="text" name="name"
value="<%= rs.getString("name") %>">

<input type="text" name="course"
value="<%= rs.getString("course") %>">

<input type="text" name="fee"
value="<%= rs.getDouble("fee") %>">

<button type="submit">Update Student</button>

</form>

<%

}

%>

</div>

</body>
</html>