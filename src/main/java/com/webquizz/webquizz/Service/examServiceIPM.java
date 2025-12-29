package com.webquizz.webquizz.Service;

import com.webquizz.webquizz.Reponsitory.exampleRepository;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.question;
import com.webquizz.webquizz.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class examServiceIPM {
    @Autowired
    private exampleRepository exampleRepository;

    public exam createExam(exam exam) {
        return exampleRepository.save(exam);
    }

    public exam findExamById(Integer examId) {
        return exampleRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));
    }

    public List<exam> getExamsByUserId(String userId) {
        return exampleRepository.findAllExamsByUserId(userId);
    }

    //    public List<exam> searchExams(String query) {
//        if (query.matches("\\d+")) {
//            // If query is numeric, search by ID or name
//            return exampleRepository.findAllExamsByIdOrName(query);
//        } else {
//            // Otherwise, search by name
//            return exampleRepository.findAllExamsByName(query);
//        }
//    }
    public List<exam> searchExamsByUserAndQuery(String userId, String query) {
        if (query.matches("\\d+")) {
            // Nếu query là số, tìm theo ID trong danh sách của user
            Integer idExam = Integer.parseInt(query);
            return exampleRepository.findExamsByUserAndIdExam(userId, idExam);
        } else {
            // Nếu query là chuỗi, tìm theo tên trong danh sách của user
            return exampleRepository.findExamsByUserAndName(userId, query);
        }
    }

    public void deleteByIdExam(Integer id) {
        exampleRepository.deleteById(id);
    }
    public exam save(exam exam) {
        return exampleRepository.save(exam);
    }

    // Thêm phương thức để cập nhật trạng thái active
    public exam updateExamStatus(Integer examId, Boolean active) {
        exam exam = findExamById(examId);
        exam.setActive(active);
        return exampleRepository.save(exam);
    }

}