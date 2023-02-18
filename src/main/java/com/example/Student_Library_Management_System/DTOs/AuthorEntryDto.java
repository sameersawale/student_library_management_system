package com.example.Student_Library_Management_System.DTOs;



public class AuthorEntryDto {

    //this is just an object that will be used to take the request from postman

    //it will contain the parameters that we want to send from postman

    //id is not here because we dont want to send it from postman

    private String name;

    private String age;

    private String country;

    private double rating;

    public AuthorEntryDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
