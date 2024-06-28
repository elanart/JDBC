package com.elanart.services;

import com.elanart.jdbc.JDBC;
import com.elanart.jdbc.JDBCUtils;
import com.elanart.pojo.Choice;
import com.elanart.pojo.Question;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionServices {
    public List<Question> getQuestions(int n) throws SQLException {
        Connection connect = JDBCUtils.getConnection();
        CallableStatement callstm = connect.prepareCall("{CALL getQuestions(?)}");

        callstm.setInt(1, n);

        ResultSet result = callstm.executeQuery();
        List<Question> questions = new ArrayList<>();
        while (result.next()){
            Question q = new Question(result.getString("id"),
                    result.getString("content"),
                    result.getInt("category_id"));
            questions.add(q);
        }

        connect.close();
        return questions;
    }

    public void addQuestion(Question question, List<Choice> choices) throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{CALL createQuestion(?, ?, ?)}");
        callableStatement.setString(1, question.getId());
        callableStatement.setString(2, question.getContent());
        callableStatement.setInt(3, question.getCategory_id());
        callableStatement.executeUpdate();

        for (Choice c: choices) {
            callableStatement = connection.prepareCall("{CALL createChoice(?, ?, ?, ?)}");
            callableStatement.setString(1, c.getId());
            callableStatement.setString(2, c.getContent());
            callableStatement.setString(3, c.getQuestion_id());
            callableStatement.setBoolean(4, c.is_correct());

            callableStatement.executeUpdate();
        }

        connection.commit();
        connection.close();;
    }
}
