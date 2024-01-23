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

            //connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet CheckUser(String email, String password) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery("SELECT * FROM useraccount WHERE email = '" + email + "' AND wachtwoord = '" + password + "'");

    }

    public ResultSet fetchUserData() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM useraccount");
    }

    public ResultSet fetchLevelData() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM leerprogramma");
    }

    public ResultSet fetchQuestionData(int leerprogrammaID) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM vraag WHERE leerprogrammaID = " + leerprogrammaID;
        return statement.executeQuery(sqlQuery);
    }

    public ResultSet fetchProgressData(int leerprogrammaID, int useraccountID) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT COUNT(v.vraagID) AS totalQuestions, COUNT(CASE WHEN p.vraag_status = 'goed' THEN 1 END) AS correctAnswers " +
                "FROM vraag v LEFT JOIN progressie p ON v.vraagID = p.vraagID AND p.useraccountID = " + useraccountID +
                " WHERE v.leerprogrammaID = '" + leerprogrammaID + "'";
        return statement.executeQuery(sqlQuery);
    }

    public void insertProgressData(int questionID, int useraccountID, String questionStatus) throws SQLException {
        String sqlQuery = "INSERT INTO progressie (vraag_status, useraccountID, vraagID) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE vraag_status = VALUES(vraag_status)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, questionStatus);
            preparedStatement.setInt(2, useraccountID);
            preparedStatement.setInt(3, questionID);

            preparedStatement.executeUpdate();
        }
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
                    "`leerprogrammaID` INT AUTO_INCREMENT NOT NULL," +
                    "`naam` varchar(45) DEFAULT NULL," +
                    "`foto` varchar(45) DEFAULT NULL," +
                    "`beschrijving` varchar(45) DEFAULT NULL," +
                    "`type_spel` varchar(45) NOT NULL," +
                    "`primary_color` varchar(7) NOT NULL," +
                    "`secondary_color` varchar(7) NOT NULL," +
                    "`accent_color` varchar(7) NOT NULL," +
                    "PRIMARY KEY (`leerprogrammaID`))");

            statement.executeUpdate("CREATE TABLE `useraccount` (" +
                    "`useraccountID` INT AUTO_INCREMENT NOT NULL," +
                    "`email` varchar(45) NOT NULL," +
                    "`wachtwoord` varchar(45) NOT NULL," +
                    "`username` varchar(45) NOT NULL," +
                    "`voornaam` varchar(45) NOT NULL," +
                    "`achternaam` varchar(45) NOT NULL," +
                    "`leeftijd` int(10) NOT NULL," +
                    "`rol` varchar(45) NOT NULL," +
                    "PRIMARY KEY (`useraccountID`))");

            statement.executeUpdate("CREATE TABLE `vraag` (" +
                    "`vraagID` INT AUTO_INCREMENT NOT NULL," +
                    "`vraag` varchar(255) NOT NULL," +
                    "`antwoord1` varchar(255) NOT NULL," +
                    "`antwoord2` varchar(255) NOT NULL," +
                    "`antwoord3` varchar(255) NOT NULL," +
                    "`antwoord4` varchar(255) NOT NULL," +
                    "`goedAntwoord` varchar(1) NOT NULL," +
                    "`leerprogrammaID` int(5) NOT NULL," +
                    "PRIMARY KEY (`vraagID`)," +
                    "FOREIGN KEY (leerprogrammaID) REFERENCES leerprogramma(leerprogrammaID))");

            statement.executeUpdate("CREATE TABLE `progressie` (" +
                    "`progressieID` INT AUTO_INCREMENT NOT NULL," +
                    "`vraag_status` varchar(45) DEFAULT NULL," +
                    "`useraccountID` int(5) NOT NULL," +
                    "`vraagID` int(10) NOT NULL," +
                    "PRIMARY KEY (`progressieID`)," +
                    "UNIQUE KEY `unique_progressie` (`useraccountID`, `vraagID`)," +
                    "FOREIGN KEY (useraccountID) REFERENCES useraccount(useraccountID)," +
                    "FOREIGN KEY (vraagID) REFERENCES vraag(vraagID))");


            statement.executeUpdate("INSERT INTO `leerprogramma` VALUES " +
                    "(1, 'De boze tovenaar', '/img/Evil Wizard.png', 'Beschrijving 1', 'Type 1', '#d2d6f1', '#acb0cc', '#684fa2'), " +
                    "(2, 'De oude heks', '/img/Witch.png', 'Beschrijving 2', 'Type 2', '#8fc178', '#729b5f', '#8d8d8d'), " +
                    "(3, 'De vuur draak', '/img/dragon.png', 'Beschrijving 3', 'Type 3', '#ff6767', '#ff4c4c', '#8d8d8d')");

            statement.executeUpdate("INSERT INTO `vraag` VALUES " +
                    "(1, 'Hoe spel je ...?', 'Appel', 'Apel', 'Apple', 'Aple', 'A', 1), " +
                    "(2, 'Welk woord hoort niet hierbij?', 'Giraffe', 'Olifant', 'Oliebol', 'paard', 'C', 1), " +
                    "(3, 'Wat is de verleden tijd van: ik eet?', 'ik eetde', 'ik at', 'ik atte', 'ik eette', 'B', 1)");

            statement.executeUpdate("INSERT INTO `useraccount` VALUES " +
                    "(1, 'test@mail.com', 'test123!', 'gebruiker', 'fname', 'lname', 21, 'user')");

            statement.executeUpdate("INSERT INTO `progressie` VALUES " +
                    "(1, 'goed', 1, 1)," +
                    "(2, 'goed', 1, 2)," +
                    "(3, 'fout', 1, 3)");

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
