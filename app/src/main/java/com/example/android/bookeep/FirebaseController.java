package com.example.android.bookeep;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;


public class FirebaseController {
    private String key = "lol";
    public void setKey(String key){
        this.key = key;
    }

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public void addNewDebt(Debt debt){
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Debts");
        databaseReference.push().setValue(debt);

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

        databaseReference.setValue(project);

        databaseReference = null;
        firebaseDatabase = null;
    }

    public void addNewPayment(Payment payment){
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Payments");
        databaseReference = databaseReference.child(payment.name);

        databaseReference.setValue(payment);

        databaseReference = null;
        firebaseDatabase = null;
    }

    public void UpdateDebt(final Debt debt){

        if(debt.amount.equals("0")){
            DeleteDebt(debt);
            return;
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Debts");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){ // the push nodes
                    Debt debt1 = child.getValue(Debt.class);

                    if(debt.from.equals(debt1.from) && debt.to.equals(debt1.to)){
                        setKey(child.getKey());
                        break;
                    }
                }

                UpdateDebtUtility(debt.amount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase = null;
        databaseReference = null;
    }

    private void UpdateDebtUtility(String amount){
        databaseReference = FirebaseDatabase.getInstance().getReference("Debts");

        databaseReference.child(key).child("amount").setValue(amount);
    }

    public void DeleteDebt(final Debt debt){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Debts");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){ // the push nodes
                    Debt debt1 = child.getValue(Debt.class);

                    if(debt.from.equals(debt1.from) && debt.to.equals(debt1.to)){
                        setKey(child.getKey());
                        break;
                    }
                }
                deleteDebtUtility();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase = null;
        databaseReference = null;
    }

    private void deleteDebtUtility(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Debts");
        databaseReference.child(key).setValue(null);
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
        databaseReference.child("developers").setValue(project.developers);

        firebaseDatabase = null;
        databaseReference = null;
    }

    public void projectComplete(Project project){
        databaseReference = FirebaseDatabase.getInstance().getReference("Projects").child(project.name);
        databaseReference.child("completedOn").setValue(project.completedOn);
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