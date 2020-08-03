package com.example.android.bookeep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDebtsFragment extends Fragment {

    RecyclerView rv;
    FirebaseRecyclerAdapter adapter;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference =firebaseDatabase.getReference("Debts");

    public ViewDebtsFragment() {
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

        FirebaseRecyclerOptions<Debt> options = new FirebaseRecyclerOptions.Builder<Debt>().setQuery(query, new SnapshotParser<Debt>() {
            @NonNull
            @Override
            public Debt parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new Debt(snapshot.child("from").getValue().toString(), snapshot.child("to").getValue().toString(),
                        snapshot.child("amount").getValue().toString(), snapshot.child("created").getValue().toString(), "");
            }
        }).build();

        adapter = new FirebaseRecyclerAdapter<Debt, ViewHolder>(options){
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_debts, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Debt debt) {
                viewHolder.setData(debt.from, debt.to, debt.amount, debt.created);
                final String to = debt.to;

                viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete(to);
                    }
                });

                viewHolder.btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Update();
                    }
                });
            }
        };

        rv.setAdapter(adapter);
    }

    public void Delete(String to){
        databaseReference.child(to).setValue(null);
        Toast.makeText(getContext(), "Record Removed", Toast.LENGTH_SHORT).show();
    }

    public void Update(){
        Toast.makeText(getContext(), "To be implemented!", Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_from, tv_to, tv_amount, tv_created;
        public Button btn_delete, btn_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_from = itemView.findViewById(R.id.tv_debt_from);
            tv_to = itemView.findViewById(R.id.tv_debt_to);
            tv_amount = itemView.findViewById(R.id.tv_debt_amount);
            tv_created = itemView.findViewById(R.id.tv_debt_on);

            btn_delete = itemView.findViewById(R.id.btn_debt_delete);
            btn_update = itemView.findViewById(R.id.btn_debt_update);
        }

        public void setData(String from, String to, String amount, String created){
            tv_from.append(" " + from);
            tv_to.append(" " + to);
            tv_amount.append(" " + amount);
            tv_created.append(" " + created);
        }
    }

    @Override
    public void onDestroyView(){
        rv.setAdapter(null);
        rv = null;
        databaseReference = null;

        super.onDestroyView();
    }

    public static ViewDebtsFragment newInstance(){
        return new ViewDebtsFragment();
    }
}
