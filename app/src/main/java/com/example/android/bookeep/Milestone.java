package com.example.android.bookeep;

public class Milestone {
    String project;
    String amount;
    String exchangeRate;
    String receivedOn;
    String description;

    public Milestone(){
//        amount = "";
//        exchangeRate = "";
//        receivedOn = "";
//        description = "";
    }

    public Milestone(String project, String amount, String exchangeRate, String receivedOn, String description){
        this.project = project;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.receivedOn = receivedOn;
        this.description = description;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setReceivedOn(String receivedOn) {
        this.receivedOn = receivedOn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
