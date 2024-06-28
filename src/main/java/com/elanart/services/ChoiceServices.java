package com.elanart.services;

import com.elanart.jdbc.JDBCUtils;
import com.elanart.pojo.Choice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoiceServices {
    public List<Choice> getChoices(String question_id) throws SQLException {
        Connection connect = JDBCUtils.getConnection();
        CallableStatement callstm = connect.prepareCall("{CALL getChoicesByQuestionId(?) }");
        callstm.setString(1, question_id);

        ResultSet result = callstm.executeQuery();
        List<Choice> choices = new ArrayList<>();
        while (result.next()){
            Choice c = new Choice(result.getString("id"),
                    result.getString("content"),
                    result.getBoolean("is_correct"),
                    result.getString("question_id"));

            choices.add(c);
        }

        connect.close();;
        return choices;
    }
}
