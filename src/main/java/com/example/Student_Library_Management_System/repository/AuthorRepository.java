package com.example.Student_Library_Management_System.repository;


import com.example.Student_Library_Management_System.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
