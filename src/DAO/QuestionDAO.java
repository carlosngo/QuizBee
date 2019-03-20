package DAO;

import Model.*;
import static DAO.DAOUtil.*;

import java.util.*;
import java.sql.*;

public class QuestionDAO {
    private static final String SQL_LIST_BY_QUIZ =
            "SELECT * FROM " + Database.QUESTION_TABLE + " WHERE FK_QuizID = ?";
    private static final String SQL_INSERT =
            "INSERT INTO " + Database.QUESTION_TABLE + " " +
            "(FK_QuizID, Prompt, Answer, ChoiceA, ChoiceB, ChoiceC, ChoiceD) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE =
            "DELETE FROM " + Database.QUESTION_TABLE + " WHERE PK_QuestionID = ?";
    private static final String SQL_UPDATE =
            "UPDATE " + Database.QUESTION_TABLE + " SET " +
                    "Prompt = ?, Answer = ?, ChoiceA = ?, ChoiceB = ?, ChoiceC = ?, ChoiceD = ? WHERE PK_QuestionID = ?";

    public ArrayList<Question> listByQuiz(int quizID) {
        ArrayList<Question> questions = new ArrayList<>();
        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_LIST_BY_QUIZ, false, quizID);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                questions.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public void create(Question question, Quiz quiz) throws IllegalArgumentException {
        if (question.getQuestionID() != -1) {
            throw new IllegalArgumentException("Question is already created, the question ID is not null.");
        }

        Object[] values = {
                quiz.getQuizID(),
                question.getPrompt(),
                question.getAnswer(),
                question.getChoices().get(0),
                question.getChoices().get(1),
                question.getChoices().get(2),
                question.getChoices().get(3)
        };

        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_INSERT, true, values);) {
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                question.setQuestionID(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating question failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Question question) throws IllegalArgumentException {
        if (question.getQuestionID() == -1) {
            throw new IllegalArgumentException("Question is not created yet, the question ID is null.");
        }

        Object[] values = {
                question.getPrompt(),
                question.getAnswer(),
                question.getChoices().get(0),
                question.getChoices().get(1),
                question.getChoices().get(2),
                question.getChoices().get(3),
                question.getQuestionID()
        };

        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_UPDATE, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating question failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Question question) {
        Object[] values = {
                question.getQuestionID()
        };

        Connection connection = Database.getConnection();
        try (
                PreparedStatement statement = prepareStatement(connection, SQL_DELETE, false, values);) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting question failed, no rows affected.");
            } else {
                question.setQuestionID(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Question map(ResultSet rs) {
        Question question = new Question();
        try {
            question.setQuestionID(rs.getInt("PK_QuestionID"));
            question.setQuizID(rs.getInt("FK_QuizID"));
            question.setPrompt(rs.getString("Prompt"));
            question.setAnswer(rs.getInt("Answer"));
            question.setChoice(0, rs.getString("ChoiceA"));
            question.setChoice(1, rs.getString("ChoiceB"));
            question.setChoice(2, rs.getString("ChoiceC"));
            question.setChoice(3, rs.getString("ChoiceD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }
}
