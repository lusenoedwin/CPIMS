package com.example.cpims;

public class handicapedcasesmodel {

    public String getName() {
        return fullname;
    }

    public void setName(String name) {
        this.fullname = name;
    }

    String fullname;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    String dob;
    String residence;

    public handicapedcasesmodel(){

        //empty constructor needed
    }
}
