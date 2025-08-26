package com.greencode.repository;

import com.greencode.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.username = :username OR u.email = :email")
    Optional<User> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);
    
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE (u.username = :username OR u.email = :email) AND u.id != :excludeId")
    Boolean existsByUsernameOrEmailExcludingId(@Param("username") String username, 
                                              @Param("email") String email, 
                                              @Param("excludeId") Long excludeId);
}
