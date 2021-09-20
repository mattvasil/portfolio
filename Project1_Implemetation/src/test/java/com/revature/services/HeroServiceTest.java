package com.revature.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.revature.models.Hero;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HeroServiceTest {
    HeroService service;
    ObjectMapper mapperMock;
    ObjectWriter writerMock;
    HttpServletRequest requestMock;
    HttpServletResponse responseMock;
    List<Hero> heroList;
    ServletOutputStream outputStreamMock;
    String json;
    @Before
    public void setUp() {

        mapperMock = Mockito.mock(ObjectMapper.class);
        writerMock = Mockito.mock(ObjectWriter.class);
        requestMock = Mockito.mock(HttpServletRequest.class);
        responseMock = Mockito.mock(HttpServletResponse.class);
        outputStreamMock = Mockito.mock(ServletOutputStream.class);
        service = new HeroService();
        heroList = new ArrayList<>();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAllHeroesTest() throws IOException {
        heroList.add(new Hero());
        when(mapperMock.writerWithDefaultPrettyPrinter()).thenReturn(writerMock);
        when(mapperMock.writerWithDefaultPrettyPrinter().writeValueAsString(heroList)).thenReturn(json);
        when(responseMock.getOutputStream()).thenReturn(outputStreamMock);

        service.getAllHeroes(requestMock, responseMock);
        verify(responseMock).setStatus(HttpServletResponse.SC_OK);
        verify(outputStreamMock).print(json);
    }

    @Test
    public void updateHeroTest() throws IOException {
        heroList.add(new Hero());
        when(mapperMock.writerWithDefaultPrettyPrinter()).thenReturn(writerMock);
        when(mapperMock.writerWithDefaultPrettyPrinter().writeValueAsString(heroList)).thenReturn(json);
        when(responseMock.getOutputStream()).thenReturn(outputStreamMock);

        service.updateHero(requestMock, responseMock);
        verify(responseMock).setStatus(HttpServletResponse.SC_OK);
        verify(outputStreamMock).print(json);
    }

    @Test
    public void deleteHeroTest() throws IOException {
        heroList.add(new Hero());
        when(mapperMock.writerWithDefaultPrettyPrinter()).thenReturn(writerMock);
        when(mapperMock.writerWithDefaultPrettyPrinter().writeValueAsString(heroList)).thenReturn(json);
        when(responseMock.getOutputStream()).thenReturn(outputStreamMock);

        service.deleteHero(requestMock, responseMock);
        verify(responseMock).setStatus(HttpServletResponse.SC_OK);
        verify(outputStreamMock).print(json);
    }

    @Test
    public void insertHeroTest() throws IOException {
        heroList.add(new Hero());
        when(mapperMock.writerWithDefaultPrettyPrinter()).thenReturn(writerMock);
        when(mapperMock.writerWithDefaultPrettyPrinter().writeValueAsString(heroList)).thenReturn(json);
        when(responseMock.getOutputStream()).thenReturn(outputStreamMock);

        service.insertHero(requestMock, responseMock);
        verify(responseMock).setStatus(HttpServletResponse.SC_OK);
        verify(outputStreamMock).print(json);
    }

    @Test
    public void getHeroTest(){service.getHeroes();}
}
