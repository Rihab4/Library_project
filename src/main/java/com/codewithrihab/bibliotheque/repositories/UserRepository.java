package com.codewithrihab.bibliotheque.repositories;

import com.codewithrihab.bibliotheque.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
