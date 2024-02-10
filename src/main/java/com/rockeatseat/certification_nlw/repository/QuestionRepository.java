/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rockeatseat.certification_nlw.repository;

import com.rockeatseat.certification_nlw.model.Question;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gusta
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByTech(String tech);
}
