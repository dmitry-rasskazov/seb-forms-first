package rasskazov.laba.laba6.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rasskazov.laba.laba6.Entity.Student;
import rasskazov.laba.laba6.Repository.StudentRepository;

import java.util.Optional;

@Slf4j
@Controller
public class StudentController
{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = {"/list", "/"})
    public ModelAndView list() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-students");
        mav.addObject("students", studentRepository.findAll());
        return mav;
    }

    @GetMapping(path = "addStudentForm")
    public ModelAndView addStudentForm() {
        ModelAndView mav = new ModelAndView("add-student-form");
        Student student = new Student();

        mav.addObject("student", student);
        return mav;
    }

    @PostMapping(path = "saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/list";
    }

    @GetMapping(path = "/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-student-form");
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = new Student();
        if(optionalStudent.isPresent()) {
            student = optionalStudent.get();
        }

        mav.addObject("student", student);
        return mav;
    }

    @GetMapping(path = "/deleteStudent")
    public String deleteStudent(@RequestParam Long id) {
        studentRepository.deleteById(id);
        return "redirect:/list";
    }
}
