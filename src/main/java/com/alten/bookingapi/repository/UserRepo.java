package com.alten.bookingapi.repository;

import com.alten.bookingapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
