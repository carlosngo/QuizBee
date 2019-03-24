package DAO;

import Model.*;
import static DAO.DAOUtil.*;

import java.util.*;
import java.sql.*;

public class QuizDAO {
    private static final String SQL_FIND_BY_ID =
            "SELECT * FROM " + Database.QUIZ_TABLE + " WHERE PK_QuizID = ?";
    private static final String SQL_FIND_BY_NAME =
            "SELECT * FROM " + Database.QUIZ_TABLE + " WHERE Name = ?";
    private static final String SQL_LIST_BY_ID =
            "SELECT * FROM " + Database.QUIZ_TABLE + " ORDER BY PK_QuizID";
    private static final String SQL_INSERT =
            "INSERT INTO " + Database.QUIZ_TABLE + " (Name, Description) VALUES (?, ?)";
    private static final String SQL_DELETE =
            "DELETE FROM " + Database.QUIZ_TABLE + " WHERE PK_QuizID = ?";
    private static final String SQL_UPDATE =
            "UPDATE " + Database.QUIZ_TABLE + " SET Name = ?, Description = ? WHERE PK_QuizID = ?";
    private static final String SQL_EXIST_NAME =
            "SELECT * FROM " + Database.QUIZ_TABLE + " WHERE Name = ?";

    public Quiz find(int quizID) { return find(SQL_FIND_BY_ID, quizID); }

    public Quiz find(String name) {
        return find(SQL_FIND_BY_NAME, name);
    }

    private Quiz find(String sql, Object... values) {
        Quiz quiz = null;
        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                quiz = map(resultSet);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }

    public ArrayList<Quiz> listById() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_BY_ID);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                quizzes.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public void create(Quiz quiz) throws IllegalArgumentException {
        if (quiz.getQuizID() != -1 || existName(quiz.getName())) {
            throw new IllegalArgumentException("Quiz is already created, the quiz ID is not null.");
        }

        Object[] values = {
                quiz.getName(),
                quiz.getDescription(),
        };

        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_INSERT, true, values);) {
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                quiz.setQuizID(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating quiz failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question q = quiz.getQuestions().get(i);
            q.setQuizID(quiz.getQuizID());
            new QuestionDAO().create(q, quiz);
        }
    }

    public void update(Quiz quiz) throws IllegalArgumentException {
        if (quiz.getQuizID() == -1) {
            throw new IllegalArgumentException("Quiz is not created yet, the quiz ID is null.");
        }

        Object[] values = {
                quiz.getName(),
                quiz.getDescription(),
                quiz.getQuizID()
        };

        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_UPDATE, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating quiz failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Quiz quiz) {
        Object[] values = {
                quiz.getQuizID()
        };

        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_DELETE, false, values);) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting quiz failed, no rows affected.");
            } else {
                quiz.setQuizID(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existName(String quizName) {
        Object[] values = {
                quizName
        };
        boolean exist = false;
        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_EXIST_NAME, false, values);
                ResultSet resultSet = statement.executeQuery()) {
            exist = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    private static Quiz map(ResultSet rs) {
        Quiz quiz = new Quiz();
        try {
            quiz.setQuizID(rs.getInt("PK_QuizID"));
            quiz.setName(rs.getString("Name"));
            quiz.setDescription(rs.getString("Description"));
            ArrayList<Question> questions = new QuestionDAO().listByQuiz(quiz.getQuizID());
            for (int i = 0; i < questions.size(); i++) {
                quiz.setQuestion(i, questions.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }
}
