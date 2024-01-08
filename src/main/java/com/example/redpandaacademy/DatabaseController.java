package com.example.redpandaacademy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseController {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/redpanda";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM useraccount");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getInt(5) + " " + resultSet.getInt(6) + " " + resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9) + " " + resultSet.getString(10) + " " + resultSet.getDate(11));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
