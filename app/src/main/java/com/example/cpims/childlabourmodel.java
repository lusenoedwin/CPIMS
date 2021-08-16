package com.example.cpims;

public class childlabourmodel {
    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    String childname;

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    String dob;

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }


    String residence;

    public childlabourmodel(){

        //empty constructor needed
    }
}
