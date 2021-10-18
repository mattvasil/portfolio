package com.revature.fauxrex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Model for Trader object
 */
@Entity
@Table(name="trader")

@Getter
@Setter
@NoArgsConstructor
public class Trader {

    /**
     * Constructor excluding Account for Trader object
     * @param id
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
    public Trader(int id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Full constructor for Trader object
     * @param id
     * @param account
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
    public Trader(int id, Account account, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.account = account;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}