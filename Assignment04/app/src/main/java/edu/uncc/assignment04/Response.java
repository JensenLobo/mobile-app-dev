package edu.uncc.assignment04;

import java.io.Serializable;

public class Response implements Serializable {
    String name, email, education, maritalStatus, livingStatus, income;

    public Response(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Response(String education, String maritalStatus, String livingStatus, String income) {
        this.education = education;
        this.maritalStatus = maritalStatus;
        this.livingStatus = livingStatus;
        this.income = income;
    }

    public Response(String name, String email, String education, String maritalStatus, String livingStatus, String income) {
        this.name = name;
        this.email = email;
        this.education = education;
        this.maritalStatus = maritalStatus;
        this.livingStatus = livingStatus;
        this.income = income;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setLivingStatus(String livingStatus) {
        this.livingStatus = livingStatus;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getLivingStatus() {
        return livingStatus;
    }

    public String getIncome() {
        return income;
    }
}
