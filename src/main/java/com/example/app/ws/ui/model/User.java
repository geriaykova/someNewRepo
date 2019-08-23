//package com.example.app.ws.ui.model;
//
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//
////TODO: помисли малко дали трябва да има абстрактен клас;
//// как ще вържеш останалите;
//// дали ще разбере ако има две entity-та (table)
//// дали ако са всички полета (колони) ще можеш да скриваш някоя
//
//@EntityListeners(AuditingEntityListener.class)
//@Entity
//@Table(name = "people")
//public class User {
//
//    private long userId;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//
//    public User(long userId, String firstName, String lastName, String email, String password) {
//        this.userId = userId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    public long getUserId() {
//        return userId;
//    }
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }
//
//    @Column(name = "firstName", nullable = false)
//    public String getFirstName() {
//        return firstName;
//    }
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @Column(name = "lastName", nullable = false)
//    public String getLastName() {
//        return lastName;
//    }
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Column(name = "email", nullable = false)
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//
//    @Column(name = "password", nullable = false)
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
