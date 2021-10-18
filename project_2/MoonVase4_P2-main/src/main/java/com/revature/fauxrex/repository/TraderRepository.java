package com.revature.fauxrex.repository;

import com.revature.fauxrex.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository layer for Trader object
 */
@Repository
public interface TraderRepository extends JpaRepository<Trader, Integer> {
    /**
     * Reading a trader by it's username
     * @param username
     * @return Trader if it exists
     */
    Optional<Trader> findByUsername(String username);
}
