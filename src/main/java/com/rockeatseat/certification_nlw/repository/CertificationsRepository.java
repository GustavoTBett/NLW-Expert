/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rockeatseat.certification_nlw.repository;

import com.rockeatseat.certification_nlw.model.Certifications;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gusta
 */
@Repository
public interface CertificationsRepository extends JpaRepository<Certifications, UUID>{
    
    @Query("Select c from Certifications c inner join c.student std where std.email = ?1 and c.technology = ?2")
    List<Certifications> findByStudentEmailAndTecnology(String email, String technology);
    
     @Query("SELECT c FROM Certifications c ORDER BY c.grade DESC LIMIT 10")
    List<Certifications> findTop10ByOrderByGradeDesc();
}
