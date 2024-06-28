package com.elanart.jdbc;

import com.elanart.pojo.Category;
import com.elanart.pojo.Choice;
import com.elanart.pojo.Question;
import com.elanart.services.ChoiceServices;
import com.elanart.services.QuestionServices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class JDBC {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        CallableStatement statement = connection.prepareCall("{CALL statsCates()}");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s - %d\n",
                    resultSet.getString(2),
                    resultSet.getInt(3));
        }

//        CallableStatement statement = connection.prepareCall("{call countQuestions(?)}");
//
//        statement.registerOutParameter(1, Types.INTEGER);
//        statement.execute();
//
//        System.out.println(statement.getInt(1));

        connection.close();

//        Question question = new Question(UUID.randomUUID().toString(),
//                "What is an abstract noun?", 1);
//
//        List<Choice> choices = new ArrayList<>();
//        choices.add(new Choice(UUID.randomUUID().toString(),
//                "A noun that refers to an idea, quality, or state",
//                true, question.getId()));
//        choices.add(new Choice(UUID.randomUUID().toString(),
//                "A noun that refers to a physical object",
//                false, question.getId()));
//        choices.add(new Choice(UUID.randomUUID().toString(),
//                "A noun that refers to a person",
//                false, question.getId()));
//        choices.add(new Choice(UUID.randomUUID().toString(),
//                "A noun that refers to a place",
//                false, question.getId()));
//
//        QuestionServices services = new QuestionServices();
//        services.addQuestion(question, choices);

//        System.out.println(UUID.randomUUID().toString());
//        QuestionServices s = new QuestionServices();
////        s.getQuestions(5).forEach(question -> System.out.printf("%s - %s %d\n",
////                question.getId(), question.getContent(), question.getCategory_id()));
//        ChoiceServices choiceServices = new ChoiceServices();
//
//        Scanner sc = new Scanner(System.in);
//        s.getQuestions(2).forEach(question -> {
//            try {
//                List<Choice> choices = choiceServices.getChoices(question.getId());
//
//                System.out.println(question.getContent());
//                for (int i = 0; i < choices.size(); i++)
//                    System.out.printf("%d - %s\n", i + 1, choices.get(i).getContent());
//                System.out.print("Your choice: ");
//                int ans = sc.nextInt();
//
//                if (choices.get(ans - 1).is_correct() == true) {
//                    System.out.println("EXACTLY");
//                } else {
//                    System.out.println("INCORRECT");
//                }
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }
}
