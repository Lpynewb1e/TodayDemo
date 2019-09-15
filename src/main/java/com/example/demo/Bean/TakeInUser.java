package com.example.demo.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TakeInUser {
    @Id
    private Long id;
    private String student_id;
    private String student_name;
    private String student_num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_num() {
        return student_num;
    }

    public void setStudent_num(String student_num) {
        this.student_num = student_num;
    }
}
