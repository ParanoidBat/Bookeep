package com.example.android.bookeep;

// https://bookeep-f9d32.firebaseio.com/ @oazaz78

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseController {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public void addNewDebt(Debt debt){
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Debts");
        databaseReference = databaseReference.child(debt.to);

        databaseReference.child("to").setValue(debt.to);
        databaseReference.child("from").setValue(debt.from);
        databaseReference.child("amount").setValue(debt.amount);
        databaseReference.child("created").setValue(debt.created);

        databaseReference = null;
        firebaseDatabase = null;
    }

    public void addNewDeveloper(Developer developer){
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Developers");
        databaseReference = databaseReference.child(developer.name);

        databaseReference.child("name").setValue(developer.name);
        databaseReference.child("joined").setValue(developer.joined);
        databaseReference.child("technologies").setValue(developer.technologies);

        databaseReference = null;
        firebaseDatabase = null;
    }

    public void addNewProject(Project project){
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Projects");
        databaseReference = databaseReference.child(project.name);

        databaseReference.child("name").setValue(project.name);
        databaseReference.child("description").setValue(project.description);
        databaseReference.child("cost").setValue(project.cost);
        databaseReference.child("clientName").setValue(project.clientName);
        databaseReference.child("startedOn").setValue(project.startedOn);

        databaseReference = null;
        firebaseDatabase = null;
    }

    public void addNewPayment(Payment payment){
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Payments");
        databaseReference = databaseReference.child(payment.name);

//        databaseReference.child("name").setValue(payment.name);
//        databaseReference.child("amount").setValue(payment.amount);
        databaseReference.setValue(payment);

        databaseReference = null;
        firebaseDatabase = null;
    }

    public void UpdateDebt(Debt debt){

        if(debt.amount.equals("0")){
            DeleteDebt(debt.to);
            return;
        }

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Debts").child(debt.to);

        databaseReference.child("amount").setValue(debt.amount);

        firebaseDatabase = null;
        databaseReference = null;
    }

    public void DeleteDebt(String to){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Debts").child(to);

        databaseReference.setValue(null);

        firebaseDatabase = null;
        databaseReference = null;
    }

    public void UpdatePayment(Payment payment){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Payments").child(payment.name);

        databaseReference.child("amount").setValue(payment.amount);
    }

    public void UpdateProject(Project project){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Projects").child(project.name);

        databaseReference.child("cost").setValue(project.cost);
        databaseReference.child("description").setValue(project.description);

        firebaseDatabase = null;
        databaseReference = null;
    }

    public void UpdateDeveloper(Developer developer){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Developers").child(developer.name);

        databaseReference.child("technologies").setValue(developer.technologies);
    }

    public void addMilestone(Milestone milestone, String name){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Milestones").child(name);

        databaseReference.push().setValue(milestone);

        firebaseDatabase = null;
        databaseReference = null;
    }
}