package com.revature.fauxrex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Model for Trade object
 */
@Entity
@Table(name="trade")

@Getter
@Setter
@NoArgsConstructor
public class Trade {

    /**
     * Full Constructor for Trade object
     * @param id
     * @param trader
     * @param timestamp
     * @param currencyPair
     * @param rate
     * @param usdAmount
     */
    public Trade(int id, Trader trader,
                 Timestamp timestamp, CurrencyPair currencyPair,
                 Double rate, Double usdAmount) {
        this.id = id;
        this.trader = trader;
        this.timestamp = timestamp;
        this.currencyPair = currencyPair;
        this.rate = rate;
        this.usdAmount = usdAmount;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="trader_id", nullable=false)
    private Trader trader;

    @Column(name="timestamp", nullable=false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="currency_pair_id", nullable = false)
    private CurrencyPair currencyPair;

    @Column(name="rate", nullable = false)
    private Double rate;

    /**
     * if this amount is negative, that indicates us dollars are being "sold" in exchange for the foreign currency
     * if this amount is positive, that indicates us dollars are being "bought" with the foreign currency
     */
    @Column(name="usd_amount", nullable = false)
    private Double usdAmount;



}


/*

TRADE
--------------------------------------
user_id | currency | rate | usd_amount
--------------------------------------
1       | EUR      | 1.18 | -1000    |
--------------------------------------
2       | EUR      | 1.18 |  1000    |
--------------------------------------

in the first row:

    since the usd_amount is negative, that means user 1 bought euros (sold dollars).
    they spent $1000 on euros.
    the exchange rate is 1.18, which means $1.18 = €1

    this translates to:

        subtracting $1000 from user 1 USD balance

        currentUSD + usd_amount

        adding ($1000 * €1/$1.18) = €847.46 to user 1 EUR balance

        currentEUR +



in the second row:

    since the usd_amount is positive, that means user 2 bought dollars (sold euros).
    they spent enough euros to acquire $1000
    the exchange rate is 1.18, which means $1.18 = €1

    this translates to:

        adding $1000 to user 2 USD balance

        subtracting ($1000 * €1/$1.18) = €847.46 from user 1 EUR balance

*Because the quotes come is as EURUSD -an indirect quote - i.e.) $1.18 = €1
 If we later want to add quotes that come in as direct quote,
 such as USDJPY then we reverse that calculation - i.e.) $1 = ¥109.94
 When USD is listed second, like EURUSD, think of USD in the numerator of the "conversion factor" ($1.18 / €1)
 When USD is listed first, like USDJPY, think if USD in the denominator of the "conversion factor" (¥109.94/$1)

 For first mvp/sprint, we are only dealing with indirectly quoted currency pairs (EURUSD, GBPUSD, NZDUSD)

 */