package com.revature.fauxrex.service;

import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service layer for TraderDetails
 */
@Service
public class TraderDetailsService implements UserDetailsService {

    /**
     * Repository layer for Trader object
     */
    @Autowired
    private final TraderRepository traderRepository;

    /**
     * Constructor for TraderDetailsService
     * @param traderRepository TraderRepository object
     */
    @Autowired
    public TraderDetailsService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    /**
     * Overridden UserDetails for Spring Security
     * @param s username of Trader
     * @return UserDetails object for Trader
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Trader trader = traderRepository.findByUsername(s).orElseThrow(RuntimeException::new);
        String username = trader.getUsername();
        String password = trader.getPassword();
        return new User(username, password, new ArrayList<>()); // ArrayList because we aren't dealing with Authorities
    }

}
