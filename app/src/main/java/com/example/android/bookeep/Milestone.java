package com.example.android.bookeep;

public class Milestone {
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

    public Milestone(String amount, String exchangeRate, String receivedOn, String description){
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.receivedOn = receivedOn;
        this.description = description;
    }
}
