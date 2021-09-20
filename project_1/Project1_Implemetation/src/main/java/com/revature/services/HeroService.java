package com.revature.services;
import com.revature.Create.ORMCreator;
import com.revature.Delete.ORMDeleter;
import com.revature.Read.ORMReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Update.ORMUpdater;
import com.revature.models.Hero;
import com.revature.util.AppAppender;
import org.apache.log4j.Logger;


public class HeroService {
    final static Logger logger = Logger.getLogger(HeroService.class);
    ObjectMapper mapper;

    public HeroService() {
        mapper = new ObjectMapper();
    }

    public void getAllHeroes(HttpServletRequest req, HttpServletResponse res){

        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getHeroes());
            res.getOutputStream().print(json);

            logger.info("The table was read");

        } catch (IOException e) {
            logger.error("Could not read table"+e.getStackTrace());
            e.printStackTrace();
        }
    }

    public List<Hero> getHeroes(){
        Optional<List<Hero>> result = ORMReader.readORM();
        return result.orElseGet(ArrayList::new);
    }


    public void insertHero(HttpServletRequest req, HttpServletResponse resp) {
        try {
            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);
            Hero hero = mapper.readValue(builder.toString(), Hero.class);
            int result = ORMCreator.insertHero(hero);

            if(result != 0){
                resp.setStatus(HttpServletResponse.SC_CREATED);
                logger.info("A record was inserted");
            } else{
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                AppAppender.fileAppender();
                logger.error("ID key already exists");
            }


        } catch (Exception e) {
            AppAppender.fileAppender();
            logger.error(e.getStackTrace());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }

    }
    public void deleteHero(HttpServletRequest req, HttpServletResponse resp) {
        boolean result = ORMDeleter.deleteHero(Integer.parseInt(req.getParameter("id")));
        if(result){
            resp.setStatus(HttpServletResponse.SC_OK);
            AppAppender.fileAppender();
            logger.info("Record was deleted");
        } else{
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            AppAppender.fileAppender();
            logger.info("The record does not exist");
        }
    }


    public void updateHero(HttpServletRequest req, HttpServletResponse resp) {
        StringBuilder builder = new StringBuilder();
        try {
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);
            Hero hero = mapper.readValue(builder.toString(), Hero.class);

            if(hero.getId() != 0){
                boolean result = ORMUpdater.update(hero);

                if(result){
                    resp.setStatus(HttpServletResponse.SC_OK);
                    String JSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hero);
                    resp.getWriter().print(JSON);
                    logger.info("A record was updated");
                }

            } else{
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                AppAppender.fileAppender();
                logger.info("Record does not exist");
            }

        } catch (IOException e) {
            AppAppender.fileAppender();
            logger.error(e.getStackTrace());
            e.printStackTrace();
        }
    }
}
