package com.revature.Delete;

import com.revature.util.ConnectionFactory;

import java.sql.SQLException;
import java.sql.Statement;

public class ORMDeleter {

    public static boolean deleteHero(int id) {
        int success = 0;

        try{

            String sql = "DELETE from heroes where id = " + id;

            Statement stmt = ConnectionFactory.getConnection().createStatement();
            success = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success == 1;
    }
}
