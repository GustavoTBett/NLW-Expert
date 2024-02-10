/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rockeatseat.certification_nlw.controller;

import com.rockeatseat.certification_nlw.dto.AlternativeResultDTO;
import com.rockeatseat.certification_nlw.dto.QuestionResultDTO;
import com.rockeatseat.certification_nlw.model.Alternatives;
import com.rockeatseat.certification_nlw.model.Question;
import com.rockeatseat.certification_nlw.repository.QuestionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gusta
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/techs/{tech}")
    public List<QuestionResultDTO> findByTech(@PathVariable String tech) {
        List<Question> result = questionRepository.findByTech(tech);

        List<QuestionResultDTO> toMap = result.stream().map(question -> mapToDTO(question)).collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapToDTO(Question question) {
        QuestionResultDTO questionResultDTO = QuestionResultDTO.builder().id(question.getId()).tech(question.getTech()).description(question.getDescription()).build();

        List<AlternativeResultDTO> alternativesResultDTOs = question.getAlternatives().stream().map(alternative -> mapAlternativeDTO(alternative))
        .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeDTO(Alternatives alternatives) {
        return AlternativeResultDTO.builder().id(alternatives.getId()).description(alternatives.getDescription()).build();
    }
}
