/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rockeatseat.certification_nlw.service;

import com.rockeatseat.certification_nlw.dto.StudentCertificationAnswerDTO;
import com.rockeatseat.certification_nlw.dto.StudentVerifyCertificationDTO;
import com.rockeatseat.certification_nlw.model.Alternatives;
import com.rockeatseat.certification_nlw.model.AnswersCertification;
import com.rockeatseat.certification_nlw.model.Certifications;
import com.rockeatseat.certification_nlw.model.Question;
import com.rockeatseat.certification_nlw.model.Students;
import com.rockeatseat.certification_nlw.repository.CertificationsRepository;
import com.rockeatseat.certification_nlw.repository.QuestionRepository;
import com.rockeatseat.certification_nlw.repository.StudentsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gusta
 */
@Service
public class StudentService {
    
    @Autowired
    private CertificationsRepository certificationsRepository;
    
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentsRepository studentRepository;
    
    public boolean verifyHasCertification(StudentVerifyCertificationDTO certificationDTO) {
        List<Certifications> certifications = certificationsRepository.findByStudentEmailAndTecnology(certificationDTO.getEmail(), certificationDTO.getTechnology());
        if (!certifications.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public Certifications execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = verifyHasCertification(new StudentVerifyCertificationDTO(dto.getEmail(), dto.getTech()));

        if  (hasCertification) {
            throw new Exception("Você já tirou sua certificação!");
        }
        
        List<Question> questions = questionRepository.findByTech(dto.getTech());
        List<AnswersCertification> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
            Question question = questions.stream().filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
            .findFirst().get();

            Alternatives findCorrect = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

            if (findCorrect.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            } else {
                questionAnswer.setCorrect(false);
            }

            AnswersCertification answersCertification = AnswersCertification.builder().answerId(questionAnswer.getAlternativeID()).questionId(questionAnswer.getQuestionID())
            .isCorrect(questionAnswer.isCorrect()).build();

            answersCertifications.add(answersCertification);
        });

        var student = studentRepository.findByEmail(dto.getEmail());

        UUID studentID;

        if (student.isEmpty()) {
            Students studentCreated = Students.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        Certifications certificationStudent = Certifications.builder().technology(dto.getTech()).studentId(studentID).grade(correctAnswers.get()).build();

        Certifications certificationStudentCreated = certificationsRepository.save(certificationStudent);
        
        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationId(certificationStudent.getId());
            answerCertification.setCertificationStudent(certificationStudent);
        });

        certificationStudent.setAnswers(answersCertifications);

        return certificationStudentCreated;
    }
}
