/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rockeatseat.certification_nlw.service;

import com.rockeatseat.certification_nlw.dto.StudentVerifyCertificationDTO;
import org.springframework.stereotype.Service;

/**
 *
 * @author gusta
 */
@Service
public class StudentService {
    public boolean verifyHasCertification(StudentVerifyCertificationDTO certificationDTO) {
        if (certificationDTO.getEmail().equals("gustavo@neocode.com") && certificationDTO.getTechnology().equals("java")) {
            return true;
        }
        return false;
    }
}
