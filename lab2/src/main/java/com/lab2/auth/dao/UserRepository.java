package com.lab2.auth.dao;

import com.lab2.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    @Query(value="SELECT u FROM users u WHERE username = ?", nativeQuery=true)
//    User findByUsername(String fooIn);
}
