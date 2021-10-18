package com.revature.fauxrex.service;

import com.revature.fauxrex.model.Account;
import com.revature.fauxrex.model.AuthenticationResponse;
import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.model.AuthenticationRequest;
import com.revature.fauxrex.repository.TraderRepository;
import com.revature.fauxrex.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer for Trader object
 */
@Service
public class TraderService {

    /**
     * Repository layer for Trader object
     */
    @Autowired
    private final TraderRepository traderRepository;

    /**
     * SPRING SECURITY
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * SPRING SECURITY
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * TraderDetailsService for spring security
     */
    @Autowired
    private TraderDetailsService traderDetailsService;

    /**
     * SPRING SECURITY
     */
    @Autowired
    private JwtUtil jwtTokenUtil;

    /**
     * Constructor for TraderService object
     * @param traderRepository TraderRepository object
     */
    @Autowired
    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    /**
     * Read operation for a Trader by it's username
     * @param username
     * @return Trader read from database
     */
    public Trader getTraderByUsername(String username){
        return traderRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    /**
     * Read operation for a Trader by it's ID
     * @param id
     * @return Trader read from database
     */
    public Trader getTraderByTraderId(Integer id) {
        return traderRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * SPRING SECURITY authentication method to verify if credentials are correct and if trader exists
     * @param authReq
     * @return RESPONSE ENTITY
     * @throws Exception
     */
    public ResponseEntity<?> authenticate(AuthenticationRequest authReq) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Invalid Credentials", e);
        }
        final UserDetails userDetails = traderDetailsService
                .loadUserByUsername(authReq.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * Create a new Trader in database
     * @param trader
     * @return the new trader persisted in database
     */
    public Trader saveNewTrader(Trader trader) {
        trader.setPassword(passwordEncoder.encode(trader.getPassword()));

        /*
        Setting their new account with initial amount of 100,000 USD
         */
        Account acc = new Account();
        acc.setUsd(100000d);
        acc.setEur(0d);
        acc.setGbp(0d);
        acc.setNzd(0d);
        trader.setAccount(acc);
        System.out.println(trader.getPassword());
        return traderRepository.save(trader);
    }

    /**
     * Update an existing Trader in the database
     * @param trader to be updated with updated values
     * @return trader after persisted to database
     */
    public Trader saveTrader(Trader trader) {
        return traderRepository.save(trader);
    }
}
