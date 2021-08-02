package com.spring.social.controller;

import com.spring.social.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestController {

   private List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student(1, "Ahmed","22"));
        students.add(new Student(2, "Fady","22"));
        students.add(new Student(3, "Wael","22"));
        students.add(new Student(5, "Ramy","22"));
        students.add(new Student(5, "Mohamed","22"));
        students.add(new Student(6, "Ayan","22"));

    }

    //http://localhost:8080/api/v1/students
    @GetMapping("/students")
    public List<Student> students(){
        return students;
    }












}
