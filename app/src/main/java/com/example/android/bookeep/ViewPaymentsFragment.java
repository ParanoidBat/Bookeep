package com.example.android.bookeep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassification;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPaymentsFragment extends Fragment {

    RecyclerView rv;
    FirebaseRecyclerAdapter adapter;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Payments");

    public ViewPaymentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);

        populate();

        return rootView;
    }

    private void populate(){
        Query query = databaseReference;

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Payment>().setQuery(query, Payment.class).build();

        adapter = new FirebaseRecyclerAdapter<Payment, ViewHolder>(options){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_payments, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Payment payment) {
                viewHolder.SetData(payment.name, String.valueOf(payment.amount));
            }
        };

        rv.setAdapter(adapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_to, tv_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_to = itemView.findViewById(R.id.tv_pay_to);
            tv_amount = itemView.findViewById(R.id.tv_pay_amount);
        }

        public void SetData(String to, String amount){
            tv_amount.append(" " + amount);
            tv_to.append(" " + to);
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onDestroyView(){
        rv.setAdapter(null);
        rv = null;
        databaseReference = null;

        super.onDestroyView();
    }

    public static ViewPaymentsFragment newInstance(){ return new ViewPaymentsFragment(); }
}
