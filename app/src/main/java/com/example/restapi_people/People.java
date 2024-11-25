package com.example.restapi_people;

public class People {

    private int id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;
    private int Age;

    public People(int id, String firstName, String lastName, String email, String phone, int age) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Phone = phone;
        Age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
