package com.example.android.bookeep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProjectsFragment extends Fragment {

    RecyclerView rv;
    FirebaseRecyclerAdapter adapter;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Projects");

    public ViewProjectsFragment() {
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

        FirebaseRecyclerOptions<Project> options = new FirebaseRecyclerOptions.Builder<Project>().setQuery(query, Project.class).build();

        adapter = new FirebaseRecyclerAdapter<Project, ViewHolder>(options){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_projects, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i, @NonNull final Project project) {
                viewHolder.SetData(project.name, project.clientName, project.description, project.cost, project.startedOn);

                viewHolder.btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        project.description = viewHolder.et_desc.getText().toString();
                        project.cost = viewHolder.et_cost.getText().toString();

                        FirebaseController firebaseController = new FirebaseController();

                        firebaseController.UpdateProject(project);
                    }
                });
            }
        };

        rv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        rv.setAdapter(null);
        rv = null;
        databaseReference = null;
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
        TextView tv_name, tv_client, tv_started;
        EditText et_desc, et_cost;
        Button btn_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_project_name);
            tv_client = itemView.findViewById(R.id.tv_project_cn);
            et_cost = itemView.findViewById(R.id.tv_project_cost);
            et_desc = itemView.findViewById(R.id.tv_project_desc);
            tv_started = itemView.findViewById(R.id.tv_project_started);
            btn_update = itemView.findViewById(R.id.btn_proj_update);
        }

        public void SetData(String name, String client, String desc, String cost, String started){
            tv_name.setText(name);
            tv_started.setText(started);
            et_desc.setText(desc);
            et_cost.setText(cost);
            tv_client.setText(client);
        }
    }

    public static ViewProjectsFragment newInstance(){ return new ViewProjectsFragment(); }
}
