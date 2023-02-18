package com.example.Student_Library_Management_System.DTOs;

public class StudentUpdateMobileReqDto {

    private int id;

    private String mobNo;

    public StudentUpdateMobileReqDto() {

    }

    public StudentUpdateMobileReqDto(int id, String mobNo) {
        this.id = id;
        this.mobNo = mobNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}
