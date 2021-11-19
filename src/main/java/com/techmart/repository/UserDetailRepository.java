package com.techmart.repository;

import com.techmart.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    @Query("SELECT u FROM UserDetail u WHERE u.email = ?1")
    UserDetail getUserDetailByEmail(String email);
}