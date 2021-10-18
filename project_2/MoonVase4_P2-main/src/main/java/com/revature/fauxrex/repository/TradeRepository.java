package com.revature.fauxrex.repository;

import com.revature.fauxrex.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository layer for Trade object
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {

    /**
     * Reading trades by their traders ID ordered by descending timestamps
     * @param traderId
     * @return List of Trader ID's by descending timestamps
     */
    List<Trade> findByTraderIdOrderByTimestampDesc(int traderId);

    /**
     * Reading a trade by it's traders ID
     * @param traderId
     * @return All trades or one trade?
     */
    List<Trade> findByTraderId(int traderId);

}
