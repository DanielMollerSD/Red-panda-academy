package com.example.redpandaacademy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseController {

    public static void main(String[] args) {
        Properties config = readConfig();

        String url = config.getProperty("url");
        String databaseName = config.getProperty("databaseName");
        String username = config.getProperty("username");
        String password = config.getProperty("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url + databaseName, username, password);

            // Check if the database exists
            if (!databaseExists(connection, databaseName)) {
                createDatabase(connection, databaseName);

                connection = DriverManager.getConnection(url + databaseName, username, password);

                createTables(connection);
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM useraccount");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " +
                        resultSet.getString(4) + " " + resultSet.getInt(5) + " " + resultSet.getInt(6) + " " +
                        resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9) + " " +
                        resultSet.getString(10) + " " + resultSet.getDate(11));
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static boolean databaseExists(Connection connection, String databaseName) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
            return resultSet.next();
        } catch (Exception e) {
            return false;
        }
    }

    private static void createDatabase(Connection connection, String databaseName) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + databaseName);
        } catch (Exception e) {
            System.out.println("Error creating database: " + e);
        }
    }

    private static void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE `leerprogramma` (" +
                    "`leerprogrammaID` char(5) NOT NULL, " +
                    "`naam` varchar(45) DEFAULT NULL, " +
                    "`foto` varchar(45) DEFAULT NULL, " +
                    "`beschrijving` varchar(45) DEFAULT NULL, " +
                    "`type_spel` varchar(45) NOT NULL, " +
                    "`aantal_vragen` int(5) NOT NULL, " +
                    "PRIMARY KEY (`leerprogrammaID`))");

            statement.executeUpdate("CREATE TABLE `useraccount` (" +
                    "`useraccountID` int(5) NOT NULL, " +
                    "`username` varchar(45) NOT NULL, " +
                    "`voornaam` varchar(45) NOT NULL, " +
                    "`achternaam` varchar(45) NOT NULL, " +
                    "`leeftijd` int(10) NOT NULL, " +
                    "`accountlevel` tinyint(4) DEFAULT NULL, " +
                    "`geslacht` varchar(45) DEFAULT NULL, " +
                    "`email` varchar(45) NOT NULL, " +
                    "`wachtwoord` varchar(45) NOT NULL, " +
                    "`rol` varchar(45) NOT NULL, " +
                    "`aanmelddatum` datetime DEFAULT NULL, " +
                    "PRIMARY KEY (`useraccountID`))");

            statement.executeUpdate("CREATE TABLE `vraag` (" +
                    "`vraagID` int(10) NOT NULL, " +
                    "`aantal_antwoorden` int(10) NOT NULL, " +
                    "`leerprogrammaID` char(5) NOT NULL, " +
                    "PRIMARY KEY (`vraagID`), " +
                    "KEY `leerprogrammaID` (`leerprogrammaID`))");

            statement.executeUpdate("CREATE TABLE `progressie` (" +
                    "`progressieID` int(5) NOT NULL, " +
                    "`stats` varchar(45) DEFAULT NULL, " +
                    "`useraccountID` int(5) NOT NULL, " +
                    "`vraagID` int(10) NOT NULL, " +
                    "PRIMARY KEY (`progressieID`), " +
                    "KEY `useraccountID` (`useraccountID`), " +
                    "KEY `vraagID` (`vraagID`))");

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
