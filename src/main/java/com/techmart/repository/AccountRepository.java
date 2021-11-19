package com.techmart.repository;

import com.techmart.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a FROM Account a WHERE a.info.id = ?1")
    Account getAccountByInfo(Integer id);

	@Query("SELECT DISTINCT ar.account FROM Authorities ar WHERE ar.role.id IN ('ADMIN','EMP')")
	List<Account> getAdministrators();
	
	@Query("UPDATE Account a SET a.status=true WHERE a.username = ?1")
	@Modifying
	public void status(String username);
	
	@Query("SELECT a FROM Account a Where a.vetificationCode = ?1")
	Account findByVerifyCode(String code);

	@Query("SELECT a FROM Account a Where a.username = ?1 and a.status = true")
	Account findByUsername(String username);

	@Query("SELECT a FROM Account a WHERE a.username = ?1")
	Account getAccountByUsername(String username);
}