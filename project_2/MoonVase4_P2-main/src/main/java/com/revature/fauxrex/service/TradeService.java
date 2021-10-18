package com.revature.fauxrex.service;


import com.revature.fauxrex.model.*;
import com.revature.fauxrex.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for Trade object
 */
@Service
public class TradeService {

    /**
     * Repository layer for Trade object and Trader object
     */
    private final TradeRepository tradeRepository;
    private final TraderService traderService;

    /**
     * Constructor for TradeService object
     * @param tradeRepository TradeRepository object
     * @param traderService TraderService object
     */
    @Autowired
    public TradeService(
            TradeRepository tradeRepository,
            TraderService traderService
    ) {
        this.tradeRepository = tradeRepository;
        this.traderService = traderService;
    }

    /**
     * Create new trade in database
     * @param trade Trade to be persisted
     * @return trade after being persisted in database
     */
    public Trade saveTrade(Trade trade) {
        Account account = trade.getTrader().getAccount();
        Double currentBalUSD = account.getUsd();
        Double usdAmount = trade.getUsdAmount();
        Double foreignAmount = -usdAmount*(1/trade.getRate()); // this calculation is happening on the front end too, more efficient: store the foreign amount in the table too and send it in with the trade
        account.setUsd(currentBalUSD+usdAmount);

        /*
        Checking which type of trade to be made (eurusd, gbpusd, nzdusd)
         */
        switch (trade.getCurrencyPair().getId()) {
            case 1: {
                Double currentForeignBal = account.getEur();
                account.setEur(currentForeignBal + foreignAmount);
                break;
            }
            case 2: {
                Double currentForeignBal = account.getGbp();
                account.setGbp(currentForeignBal + foreignAmount);
                break;
            }
            case 3: {
                Double currentForeignBal = account.getNzd();
                account.setNzd(currentForeignBal + foreignAmount);
                break;
            }
        }

        return tradeRepository.save(trade);
    }

    /**
     * Read Trade by it's ID from the database
     * @param id
     * @return List with one Trade from the database (found by it's ID)
     */
    public List<Trade> getTradeById(int id) {


        return tradeRepository.findByTraderId(id);
    }

}
