package com.revature.fauxrex.controller;

import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.model.AuthenticationRequest;
import com.revature.fauxrex.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Controller layer for Trader objects
 */
@CrossOrigin
@Controller
@RequestMapping("/trader")
public class TraderController {
    /**
     * Service layer for Trader object
     */
    @Autowired
    private final TraderService traderService;

    /**
     * Controller layer for Trader object
     * @param traderService TraderService object
     */
    @Autowired
    public TraderController(TraderService traderService) {this.traderService = traderService;}

    /**
     * Create operation for a Trader object
     * @param t Trader to be persisted
     * @return Trader that has been persisted
     */
    @PostMapping("/register")
    public @ResponseBody
    Trader createTrader(@RequestBody Trader t) {
        return traderService.saveNewTrader(t);
    }

    /**
     * Update operation for Trader object
     * @param authReq Authorized request from client side
     * @return ReponseEntity representing status
     * @throws Exception
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authReq) throws Exception {
        return traderService.authenticate(authReq);
    }

    /**
     * Read operation for Trader by it's username
     * @return Trader read from database by it's username
     */
    @GetMapping("/username")
    public @ResponseBody Trader getTraderByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return traderService.getTraderByUsername(username);
    }
}
