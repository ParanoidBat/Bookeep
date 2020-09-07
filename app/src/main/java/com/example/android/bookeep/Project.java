package com.example.android.bookeep;

public class Project {
    public String name;
    public String description;
    public String cost;
    public String clientName;
    public String startedOn;
    public String completedOn;

    public Project(){
    }

    public Project(String name, String description, String cost, String clientName, String startedOn, String completedOn){
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.clientName = clientName;
        this.startedOn = startedOn;
        this.completedOn = completedOn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public void setCompletedOn(String completedOn) {
        this.completedOn = completedOn;
    }
}
