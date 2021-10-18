package com.revature.fauxrex.service;


import com.revature.fauxrex.model.AuthenticationRequest;
import com.revature.fauxrex.model.AuthenticationResponse;
import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TraderRepository;


import com.revature.fauxrex.util.JwtUtil;
import org.apache.catalina.connector.Response;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class TraderServiceTest {

    static Trader tedTest = new Trader(1,
            "ted_test", "password",
            "Ted", "Test");

    @Autowired
    private TraderService service;

    @MockBean
    private TraderRepository repository;

    @MockBean
    private UserDetails userDetails;

    @MockBean
    private TraderDetailsService TDS;

    @MockBean
    private AuthenticationManager manager;

    @MockBean
    private AuthenticationRequest request;

    @MockBean
    private UsernamePasswordAuthenticationToken UPToken;

    @MockBean
    private AuthenticationResponse response;

    @MockBean
    private JwtUtil token;

    @MockBean
    private ResponseEntity entity;

    @Before
    public void init() {
        userDetails = org.mockito.Mockito.mock(UserDetails.class);
        response = org.mockito.Mockito.mock(AuthenticationResponse.class);
    }

    @Test
    public void getTraderByUsernameTest() {
        when(repository.findByUsername(tedTest.getUsername())).thenReturn(java.util.Optional
                .of(tedTest));
        assertEquals(1, service.getTraderByUsername(tedTest.getUsername())
                .getId());
    }

    @Test
    public void getTraderByTraderIDTest() {
        when(repository.findById(1)).thenReturn(java.util.Optional
                .of(tedTest));
        assertEquals(1, service.getTraderByTraderId(1)
                .getId());
    }

    @Test
    public void saveTraderTest() {
        when(repository.save(tedTest)).thenReturn(tedTest);
        assertEquals(1, service.saveTrader(tedTest).getId());
    }



    @Test
    void saveNewTraderTest() {
        when(repository.save(tedTest)).thenReturn(tedTest);
        assertEquals(1, service.saveNewTrader(tedTest).getId());
    }

//    @Test
//    void authenticateTest() throws Exception {
//      //  when(manager.authenticate(UPToken)).equals()
//        when(request.getUsername()).thenReturn(tedTest.getUsername());
//        when(request.getPassword()).thenReturn(tedTest.getPassword());
//        when(TDS.loadUserByUsername(tedTest.getUsername())).thenReturn(userDetails);
//        when(token.generateToken(userDetails)).thenReturn("ok");
//        when(entity.ok(response)).thenReturn(entity);
//        //when(entity.ok(response)).thenReturn(entity);
//        //verify(status().isOk());
//        assertEquals(entity, service.authenticate(request));
//
//
//    }


}
