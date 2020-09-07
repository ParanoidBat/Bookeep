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
public class AddDebtFragment extends Fragment {

    private EditText et_from, et_to, et_amount;
    private Button btn_add;
    private String from, to, amount;
    private FirebaseController firebaseController;
    private Debt debt;

    public AddDebtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_debt, container, false);

        et_from = rootView.findViewById(R.id.et_from);
        et_to = rootView.findViewById(R.id.et_to);
        et_amount = rootView.findViewById(R.id.et_amount);

        btn_add = rootView.findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

                if(from.isEmpty() || to.isEmpty() || amount.isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "All fields required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    clearFields();

                    debt = new Debt(from, to, amount, LocalDate.now().toString());

                    firebaseController = new FirebaseController();
                    firebaseController.addNewDebt(debt);

                    Toast.makeText(getActivity().getBaseContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private void clearFields(){
        et_from.setText("");
        et_to.setText("");
        et_amount.setText("");
    }

    private void getData(){
        from = et_from.getText().toString();
        to = et_to.getText().toString();
        amount = et_amount.getText().toString();
    }

    public static AddDebtFragment newInstance(){
        return new AddDebtFragment();
    }
}
