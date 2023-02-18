package com.example.Student_Library_Management_System.controllers;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobileReqDto;
import com.example.Student_Library_Management_System.model.Student;
import com.example.Student_Library_Management_System.service.StudentService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("/getByEmail/{email}")
    public String getNameByEmail(@PathVariable("email") String email){
        return studentService.findNameByEmail(email);
    }

    @GetMapping("/getByCountry/{country}")
    public List<Student> getByCountry(@PathVariable("country")String country){
        return studentService.findByCountry(country);
    }

    @PutMapping("/updateMob")
    public String updateMob(@RequestBody StudentUpdateMobileReqDto studentUpdateMobileReqDto){
        return studentService.updateMobile(studentUpdateMobileReqDto);
    }
}
