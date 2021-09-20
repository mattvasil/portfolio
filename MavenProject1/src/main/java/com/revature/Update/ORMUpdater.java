package com.revature.Update;

import com.revature.models.Hero;
import com.revature.util.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ORMUpdater {
    public static boolean update(Hero hero) {
        int success = 0;

        try{
            String sql = "UPDATE heroes set first = ?," +
                    " last = ?, alias = ?" +
                    "where id = ?";

            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);

            stmt.setString(1, hero.getFirst());
            stmt.setString(2, hero.getLast());
            stmt.setString(3, hero.getAlias());
            stmt.setInt(4, hero.getId());

            success = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success == 1;
    }
}
