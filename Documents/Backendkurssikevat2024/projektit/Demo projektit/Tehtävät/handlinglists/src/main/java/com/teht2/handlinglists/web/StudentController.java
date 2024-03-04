package com.teht2.handlinglists.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.teht2.handlinglists.domain.Student;

@Controller
public class StudentController {

    @GetMapping("/hello")
    public String hello(Model model) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Kate", "Cole"));
        students.add(new Student("Dan", "Brown"));
        students.add(new Student("Mike", "Mars"));


        model.addAttribute("message", "Welcome to the Haaga-Helia!");
        model.addAttribute("students", students);


        return "studentlist";
    }
}
