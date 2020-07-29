package com.example.android.bookeep;

import android.widget.Toast;

public class Debt {
    public String from = "";
    public String to = "";
    public String amount = "";
    public String created = "";
    public String cleared = "";

    public Debt(){}

    public Debt(String from, String to, String amount, String created, String cleared){
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.created = created;
        this.cleared = cleared;
    }

    public int updateDebt(int amount){
        int x = Integer.parseInt(this.amount) + amount;

        this.amount = Integer.toString(x);

        return x;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setCleared(String cleared) {
        this.cleared = cleared;
    }
}
