package com.informatorio.actividadfinal.repository;

import java.time.LocalDate;
import java.util.List;

import com.informatorio.actividadfinal.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByCity(String city);
    List<User> findAllByCreationDateIsAfter(LocalDate registDate);
    User findByEmail(String email);
}
