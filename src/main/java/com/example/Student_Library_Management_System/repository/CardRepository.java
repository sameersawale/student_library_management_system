package com.example.Student_Library_Management_System.repository;

import com.example.Student_Library_Management_System.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
