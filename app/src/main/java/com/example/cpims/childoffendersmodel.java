package com.example.cpims;

public class childoffendersmodel {
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    String fullname;

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    String dob;


    public String getDateofoffence() {
        return dateofoffence;
    }

    public void setDateofoffence(String dateofoffence) {
        this.dateofoffence = dateofoffence;
    }

    public String dateofoffence;

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }


    String residence;

    public childoffendersmodel(){

        //empty constructor needed
    }
}

