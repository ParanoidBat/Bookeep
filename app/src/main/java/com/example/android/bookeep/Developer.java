package com.example.android.bookeep;

import java.util.ArrayList;
import java.util.Vector;

public class Developer {
    public String name;
    public String joined;

    public Vector<String> workingOn;
    public Vector<String> workedOn;
    public ArrayList<String> technologies;

    public Developer(){}

//    public Developer(){
//        name = "";
//        joined = "";
//        workedOn = new Vector<String>();
//        workingOn = new Vector<String>();
//        technologies = new ArrayList<String>();
//    }

    public Developer(String name, String joined, Vector<String> current, Vector<String> completed, ArrayList<String> fields){
        this.name = name;
        this.joined = joined;

        if(completed != null){
            workedOn = new Vector<String>();
            workedOn = (Vector) completed.clone();
        }

        if(current!= null){
            workingOn = new Vector<String>();
            workingOn = (Vector) current.clone();
        }

        if(fields != null){
            technologies = new ArrayList<String>();
            technologies = (ArrayList) fields.clone();
        }
        else technologies = new ArrayList<String>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public void setWorkingOn(Vector<String> workingOn) {
        this.workingOn = workingOn;
    }

    public void setWorkedOn(Vector<String> workedOn) {
        this.workedOn = workedOn;
    }

    public void setTechnologies(ArrayList<String> technologies) {
        this.technologies = technologies;
    }
}