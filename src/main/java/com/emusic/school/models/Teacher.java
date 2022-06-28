package com.emusic.school.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName, lastName, email, password, subject;

    @OneToMany(mappedBy="teacher", fetch=FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();
    public Teacher() {}

    public Teacher(String firstName, String lastName, String email, String password, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourses() {
        return courses;
    }
    public void addCourses(Course course) {
        course.setTeacher(this);
        courses.add(course);
    }

    public String getSubject() {return subject;}

    public void setSubject(String subject) {this.subject = subject;}
}
