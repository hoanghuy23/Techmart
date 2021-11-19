package com.techmart.repository;

import com.techmart.model.Account;
import com.techmart.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
    @Query("SELECT DISTINCT a FROM Authorities a WHERE a.account IN ?1")
    List<Authorities> authoritiesOf(List<Account> accounts);
}