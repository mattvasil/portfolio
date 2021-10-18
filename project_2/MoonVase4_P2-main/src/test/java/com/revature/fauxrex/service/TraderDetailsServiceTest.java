package com.revature.fauxrex.service;

import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TraderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TraderDetailsServiceTest {

    static Trader tedTest = new Trader(1,
            "ted_test", "password",
            "Ted", "Test");


    @Autowired
    private TraderDetailsService service;

    @MockBean
    private TraderRepository repository;


    @Test
    void loadUserByUsername() {
        when(repository.findByUsername(tedTest.getUsername())).thenReturn(java.util.Optional.ofNullable(tedTest));
     //   when(tedTest.getUsername()).thenReturn("ted_test");
        assertEquals(tedTest.getUsername(), service.loadUserByUsername("ted_test").getUsername());
    }
}