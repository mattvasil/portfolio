package com.revature.Read;
import com.revature.models.Hero;
import com.revature.util.ConnectionFactory;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ORMReader{
    Logger logger = Logger.getLogger(ORMReader.class);

    public static Optional<List<Hero>> readORM(){
        List<Hero> allHeroes = new ArrayList<>();
        String sql = "select * from heroes";
        Statement stmt;
        ResultSet rs;

        try(Connection conn = ConnectionFactory.getConnection()){


            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                allHeroes.add(new Hero(
                        rs.getInt("id"),
                        rs.getString("first"),
                        rs.getString("last"),
                        rs.getString("alias")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(allHeroes);
    }
}
