package com.revature.fauxrex.service;

import com.revature.fauxrex.model.Account;
import com.revature.fauxrex.model.CurrencyPair;
import com.revature.fauxrex.model.Trade;
import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TradeRepository;
import com.revature.fauxrex.repository.TraderRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TradeServiceTest {
    static Account acc = new Account(1, 1232.23, 34.34, 453.45, 345.45);

    static Trader tedTest = new Trader(1, acc, "ted_test","password", "Ted","Test");

    static CurrencyPair cp = new CurrencyPair( 1, "eurusd");

    static CurrencyPair cp2 = new CurrencyPair( 2, "gbpusd");

    static CurrencyPair cp3 = new CurrencyPair( 3, "nzdusd");

    static long now = System.currentTimeMillis();

    static Timestamp sqlTimestamp = new Timestamp(now);

    static Trade tradeTest = new Trade( 1, tedTest , sqlTimestamp , cp, 1.32, 12535.24);

    static Trade tradeTest2 = new Trade( 1, tedTest , sqlTimestamp , cp2, 1.32, 12535.24);

    static Trade tradeTest3 = new Trade( 1, tedTest , sqlTimestamp , cp3, 1.32, 12535.24);



    @Autowired
    private TradeService service;

    @MockBean
    private TradeRepository repository;

    @MockBean
    private TraderRepository repo;

    @MockBean
    private TraderService tservice;

    @MockBean
    private Account account;



    @Test
    void saveTradeEUSDTest() throws JSONException, IOException {
        when(tservice.getTraderByTraderId(1)).thenReturn(tedTest);
        when(repo.findById(tedTest.getId())).thenReturn(java.util.Optional.ofNullable(tedTest));
        when(repository.save(tradeTest)).thenReturn(tradeTest);
        assertEquals(1, service.saveTrade(tradeTest).getId());
    }

    @Test
    void saveTradeGUSDTest() throws JSONException, IOException {
        when(tservice.getTraderByTraderId(1)).thenReturn(tedTest);
        when(repo.findById(tedTest.getId())).thenReturn(java.util.Optional.ofNullable(tedTest));
        when(repository.save(tradeTest2)).thenReturn(tradeTest2);
        assertEquals(1, service.saveTrade(tradeTest2).getId());
    }

    @Test
    void saveTradeNUSDTest() throws JSONException, IOException {
        when(tservice.getTraderByTraderId(1)).thenReturn(tedTest);
        when(repo.findById(tedTest.getId())).thenReturn(java.util.Optional.ofNullable(tedTest));
        when(repository.save(tradeTest3)).thenReturn(tradeTest3);
        assertEquals(1, service.saveTrade(tradeTest3).getId());
    }

    @Test
    void getTradeByIdTest() {
        when(repository.findByTraderId(tedTest.getId())).thenReturn(Stream.of(tradeTest).collect(Collectors.toList()));
        assertEquals(1,  service.getTradeById(tedTest.getId()).size());
    }

//Stream.of(tradeTest).collect(Collectors.toList())
}