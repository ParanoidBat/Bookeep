package com.example.android.bookeep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProjectFragment extends Fragment {
    EditText et_name, et_client_name, et_cost, et_desc;
    Button btn_add;
    String name, client_name, cost, desc;

    public AddProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_project, container, false);

        et_name = rootView.findViewById(R.id.et_project_name);
        et_desc = rootView.findViewById(R.id.et_desc);
        et_cost = rootView.findViewById(R.id.et_cost);
        et_client_name = rootView.findViewById(R.id.et_client_name);

        btn_add = rootView.findViewById(R.id.btn_add_project);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

                if(name.isEmpty() || cost.isEmpty() || desc.isEmpty() || client_name.isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "All Fields Required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    clearFields();

                    Project project = new Project(name, desc, cost, client_name, LocalDate.now().toString(), "");

                    FirebaseController firebaseController = new FirebaseController();
                    firebaseController.addNewProject(project);

                    Toast.makeText(getActivity().getBaseContext(), "Added Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    public static AddProjectFragment newInstance(){
        return new AddProjectFragment();
    }

    private void getData(){
        name = et_name.getText().toString();
        client_name = et_client_name.getText().toString();
        cost = et_cost.getText().toString();
        desc = et_desc.getText().toString();
    }

    private void clearFields(){
        et_client_name.setText("");
        et_cost.setText("");
        et_desc.setText("");
        et_name.setText("");
    }
}
