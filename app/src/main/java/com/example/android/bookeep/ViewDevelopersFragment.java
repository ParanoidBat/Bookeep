package com.example.android.bookeep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDevelopersFragment extends Fragment {

    RecyclerView rv;
    FirebaseRecyclerAdapter adapter;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("Developers");

    public ViewDevelopersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(false);

        populate();

        return rootView;
    }

    private void populate(){
        Query query = databaseReference;

        FirebaseRecyclerOptions<Developer> options = new FirebaseRecyclerOptions.Builder<Developer>().setQuery(query, Developer.class).build();

        adapter = new FirebaseRecyclerAdapter<Developer, ViewHolder>(options){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_developer, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Developer developer) {
                viewHolder.SetData(developer.name, developer.joined, developer.technologies);
            }
        };

        rv.setAdapter(adapter);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_name, tv_joined, tv_techs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_dev_name);
            tv_joined = itemView.findViewById(R.id.tv_dev_joined);
            tv_techs = itemView.findViewById(R.id.tv_dev_tech);
        }

        public void SetData(String name, String joined, ArrayList<String> techs){
            tv_name.append(" " + name);
            tv_joined.append(" " + joined);
            tv_techs.append(" " + techs.get(0));

            for(int i = 1; i < techs.size(); i++){
                tv_techs.append(", " + techs.get(i));
            }
        }
    }

    public static ViewDevelopersFragment newInstance(){ return new ViewDevelopersFragment(); }
}
