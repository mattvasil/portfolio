package com.revature.Create;
import com.revature.models.Hero;
import com.revature.util.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class ORMCreator {

    public static int insertHero(Hero hero) {

        try{
            PreparedStatement stmt;
            String sql;
            if(hero.getId() != 0){
                sql = "insert into heroes (id, first, last, alias) " +
                        "values (?, ?, ?, ?)";
                stmt = ConnectionFactory.getConnection().prepareStatement(sql);
                stmt.setInt(1, hero.getId());
                stmt.setString(2, hero.getFirst());
                stmt.setString(3, hero.getLast());
                stmt.setString(4, hero.getAlias());


            } else{
                sql = "insert into heroes (first, last, alias) " +
                        "values (?, ?, ?)";
                stmt = ConnectionFactory.getConnection().prepareStatement(sql);
                stmt.setString(1, hero.getFirst());
                stmt.setString(2, hero.getLast());
                stmt.setString(3, hero.getAlias());
            }
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

