package com.webquizz.webquizz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "exam")
public class exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exam")
    private Integer idExam;

    @Column(name = "name_exam")
    private String nameExam;

    @Column(name = "test")
    private String test;

    @Column(name = "date_exam")
    private LocalDateTime dateExam;

    @Column(name = "active")
    private Boolean active = true; // Thêm lại trường active với giá trị mặc định là true

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    private user user;

    // Getters và Setters
    public Integer getIdExam() {
        return idExam;
    }

    public void setIdExam(Integer idExam) {
        this.idExam = idExam;
    }

    public String getNameExam() {
        return nameExam;
    }

    public void setNameExam(String nameExam) {
        this.nameExam = nameExam;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public LocalDateTime getDateExam() {
        return dateExam;
    }

    public void setDateExam(LocalDateTime dateExam) {
        this.dateExam = dateExam;
    }

    public String getFormattedDateExam() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateExam != null ? dateExam.format(formatter) : "";
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}