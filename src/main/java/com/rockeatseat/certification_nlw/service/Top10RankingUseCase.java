/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rockeatseat.certification_nlw.service;

import com.rockeatseat.certification_nlw.model.Certifications;
import com.rockeatseat.certification_nlw.repository.CertificationsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gusta
 */
@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationsRepository certificationStudentRepository;
    
    public List<Certifications> execute() {
        return certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }
}