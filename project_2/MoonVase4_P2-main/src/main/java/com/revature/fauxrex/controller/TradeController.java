package com.revature.fauxrex.controller;

import com.revature.fauxrex.model.*;
import com.revature.fauxrex.service.TradeService;
import com.revature.fauxrex.service.TraderService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;


/**
 * Controller layer for Trade objects
 */
@CrossOrigin
@Controller
@RequestMapping("/trade")
public class TradeController {

    /**
     * Service layer for Trade objects
     */
    @Autowired
    private final TradeService tradeService;

    /**
     * Service layer for Trader objects
     */
    @Autowired
    private final TraderService traderService;

    /**
     * Constructor for TradeController
     *
     * @param tradeService TradeService object
     * @param traderService TraderService object
     */
    @Autowired
    public TradeController(TradeService tradeService, TraderService traderService) {
        this.tradeService = tradeService;
        this.traderService = traderService;
    }

    /**
     * Create operation for Trade object
     * @param trade Trade object to persist
     * @return Trade object that has been persisted
     *
     * @throws JSONException
     * @throws IOException
     */
    @PostMapping
    public @ResponseBody
    Trade createTrade(@RequestBody Trade trade) throws JSONException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Trader trader = traderService.getTraderByUsername(username);
        trade.setTrader(trader);
        return tradeService.saveTrade(trade);
    }

    /**
     * Reading operation for a Trade by it's ID
     * @return Trade read from the database with it's ID
     */
    @GetMapping("/history")
    public @ResponseBody
    List<Trade> findTradeById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Trader trader = traderService.getTraderByUsername(username);
        return tradeService.getTradeById(trader.getId());
    }

    //Endpoint to serve API credentials to front end
    @GetMapping("/cg")
    public @ResponseBody
    String cg() throws JSONException {
        JSONObject obj = new JSONObject();
        String password = System.getenv("Password");
        String username = System.getenv("UserName");
        String appkey = System.getenv("AppKey");

        obj.put("Password", password);
        obj.put("UserName", username);
        obj.put("AppKey", appkey);
        obj.put("AppVersion", 1);

        return obj.toString();
    }



}
