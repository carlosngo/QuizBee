package DAO;

import java.sql.*;

public final class Database {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/quizbee";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "password";

    public static final String QUIZ_TABLE = "quizbee.quiz";
    public static final String QUESTION_TABLE = "quizbee.question";

    private static Connection con = null;

    private Database() { }

    public static Connection getConnection() {
        try {
            if (con == null)
                con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch(SQLException e) {
            System.out.println("Cannot connect to database.");
        }
        return con;
    }
}
