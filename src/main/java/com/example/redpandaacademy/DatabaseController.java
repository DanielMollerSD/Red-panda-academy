package com.example.redpandaacademy;

import java.sql.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseController {
    private Connection connection;
    public DatabaseController() {
        Properties config = readConfig();

        String url = config.getProperty("url");
        String databaseName = config.getProperty("databaseName");
        String username = config.getProperty("username");
        String password = config.getProperty("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Check if the database exists
            if (!databaseExists(url, databaseName, username, password)) {
                createDatabase(url, databaseName, username, password);
                createTables(url, databaseName, username, password);
            }

            // Connect to database
            connection = DriverManager.getConnection(url + databaseName, username, password);

            /*//Start of select statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM useraccount");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " +
                        resultSet.getString(4) + " " + resultSet.getInt(5) + " " + resultSet.getInt(6) + " " +
                        resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9) + " " +
                        resultSet.getString(10) + " " + resultSet.getDate(11));
            }*/

            //connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet fetchUserData() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM useraccount");
    }

    public ResultSet fetchLevelData() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM leerprogramma");
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    private static boolean databaseExists(String url, String databaseName, String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
            return resultSet.next();
        } catch (Exception e) {
            return false;
        }
    }

    private static void createDatabase(String url, String databaseName, String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + databaseName);
            connection.close();
        } catch (Exception e) {
            System.out.println("Error creating database: " + e);
        }
    }

    private static void createTables(String url, String databaseName, String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(url + databaseName, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE `leerprogramma` (" +
                    "`leerprogrammaID` char(5) NOT NULL," +
                    "`naam` varchar(45) DEFAULT NULL," +
                    "`foto` varchar(45) DEFAULT NULL," +
                    "`beschrijving` varchar(45) DEFAULT NULL," +
                    "`type_spel` varchar(45) NOT NULL," +
                    "`primary_color` varchar(7) NOT NULL," +
                    "`secondary_color` varchar(7) NOT NULL," +
                    "`accent_color` varchar(7) NOT NULL," +
                    "PRIMARY KEY (`leerprogrammaID`))");

            statement.executeUpdate("CREATE TABLE `useraccount` (" +
                    "`useraccountID` int(5) NOT NULL," +
                    "`email` varchar(45) NOT NULL," +
                    "`wachtwoord` varchar(45) NOT NULL," +
                    "`username` varchar(45) NOT NULL," +
                    "`voornaam` varchar(45) NOT NULL," +
                    "`achternaam` varchar(45) NOT NULL," +
                    "`leeftijd` int(10) NOT NULL," +
                    "`rol` varchar(45) NOT NULL," +
                    "PRIMARY KEY (`useraccountID`))");

            statement.executeUpdate("CREATE TABLE `vraag` (" +
                    "`vraagID` int(10) NOT NULL,\n" +
                    "  `aantwoord1` varchar(255) NOT NULL," +
                    "  `aantwoord2` varchar(255) NOT NULL," +
                    "  `aantwoord3` varchar(255) NOT NULL," +
                    "  `aantwoord4` varchar(255) NOT NULL," +
                    "  `leerprogrammaID` char(5) NOT NULL," +
                    "PRIMARY KEY (`vraagID`), " +
                    "FOREIGN KEY (leerprogrammaID) REFERENCES leerprogramma(leerprogrammaID))");


            statement.executeUpdate("CREATE TABLE `progressie` (" +
                    "`progressieID` int(5) NOT NULL," +
                    "`vraag_status` varchar(45) DEFAULT NULL," +
                    "`useraccountID` int(5) NOT NULL," +
                    "`vraagID` int(10) NOT NULL," +
                    "FOREIGN KEY (useraccountID) REFERENCES useraccount(useraccountID)," +
                    "FOREIGN KEY (vraagID) REFERENCES vraag(vraagID))");

            connection.close();

        } catch (Exception e) {
            System.out.println("Error creating tables: " + e);
        }
    }

    private static Properties readConfig() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(".env")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
