/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rockeatseat.certification_nlw.controller;

import com.rockeatseat.certification_nlw.dto.StudentVerifyCertificationDTO;
import com.rockeatseat.certification_nlw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gusta
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService; 
    
    @PostMapping("/veriifyHasCertification")
    public String veriifyHasCertification(@RequestBody StudentVerifyCertificationDTO certificationDTO) {
        if (studentService.verifyHasCertification(certificationDTO)) {
            return "pode";
        }
        return "não pode";
    }
}
