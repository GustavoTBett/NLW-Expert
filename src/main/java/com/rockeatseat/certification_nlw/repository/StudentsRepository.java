/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rockeatseat.certification_nlw.repository;

import com.rockeatseat.certification_nlw.model.Students;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gusta
 */
@Repository
public interface StudentsRepository extends JpaRepository<Students, UUID> {
    Optional<Students> findByEmail(String email);
}
