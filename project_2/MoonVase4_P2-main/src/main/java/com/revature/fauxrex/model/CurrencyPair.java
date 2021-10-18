package com.revature.fauxrex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Model for CurrencyPair object
 */
@Entity
@Table(name="currency_pair")

@Getter
@Setter
@NoArgsConstructor
public class CurrencyPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="currency_pair", nullable = false)
    private String currencyPair;

    /**
     * Full constructor for CurrencyPair object
     * @param id
     * @param currencyPair
     */
    public CurrencyPair(int id, String currencyPair){
        this.id = id;
        this.currencyPair = currencyPair;
    }
}