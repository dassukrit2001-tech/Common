package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {

    static Connection con = null;

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL server first
            Connection tempCon = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    "root",
                    "password");

            // Create database automatically
            PreparedStatement ps1 = tempCon.prepareStatement(
                    "CREATE DATABASE IF NOT EXISTS studentdb");

            ps1.executeUpdate();

            // Connect to studentdb
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "password");

            // Create users table automatically
            PreparedStatement ps2 = con.prepareStatement(

                    "CREATE TABLE IF NOT EXISTS users(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100)," +
                    "email VARCHAR(100)," +
                    "password VARCHAR(100))");

            ps2.executeUpdate();

            // Create students table automatically
            PreparedStatement ps3 = con.prepareStatement(

                    "CREATE TABLE IF NOT EXISTS students(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100)," +
                    "course VARCHAR(100)," +
                    "fee DOUBLE)");

            ps3.executeUpdate();

            System.out.println("Database and Tables Ready");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}