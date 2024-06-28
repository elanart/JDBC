package com.elanart.services;

import com.elanart.jdbc.JDBCUtils;
import com.elanart.pojo.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryServices {
    public List<Category> getCategories() throws SQLException {
        Connection connect = JDBCUtils.getConnection();
        Statement stm = connect.createStatement();

        ResultSet result = stm.executeQuery("SELECT * FROM Category");

        List<Category> categories = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");

            Category c = new Category(id, name);
            categories.add(c);
        }

        return categories;
    }
}
