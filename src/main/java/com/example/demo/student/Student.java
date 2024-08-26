package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Student") // for hibernate
@Table(
    name = "student",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "student_email_unique",
            columnNames = {"email"}
        )
    }
) // for table in our db
public class Student {

    @Id
    @SequenceGenerator(
        name = "student_id_sequence",
        sequenceName =  "student_id_sequence",
        initialValue = 1,
        allocationSize = 1 // increment by
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_id_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @Column(
        name = "age",
        nullable = false
    )
    @Transient
    private Integer age;
    private LocalDate dob;

    @Column(
        name = "email",
        nullable = false,
        columnDefinition = "TEXT"        
    )
    private String email;


    public Student() {
    }

    public Student(Long id, String name, LocalDate dob, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Student(String name, LocalDate dob, String email) {
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + ", dob=" + dob + ", email=" + email + "]";
    }

}
