package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.Response;

public class IdentificationFragment extends Fragment {
    private String name;
    private String email;
    public IdentificationFragment() {
        // Required empty public constructor
    }

    public static IdentificationFragment newInstance(){
        return new IdentificationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_identification, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonNext = view.findViewById(R.id.buttonNext);
        EditText editTextName = view.findViewById(R.id.editTextName);
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        Response mResponse = new Response(name, email);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String role = "Student";
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == R.id.radioButtonStudent){
                    role = "Student";
                }
                else if(selectedId == R.id.radioButtonEmployee){
                    role = "Employee";
                }
                else if(selectedId == R.id.radioButtonOther){
                    role = "Other";
                }

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter valid Name!", Toast.LENGTH_LONG).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter valid Email!", Toast.LENGTH_LONG).show();
                } else{
                    mResponse.setName(name);
                    mResponse.setEmail(email);
                    mListener.goToDemographic(mResponse);
                }
            }
        });
    }
    IdentificationFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (IdentificationFragmentListener) context;
    }

    public interface IdentificationFragmentListener {
        void goToDemographic(Response response);
        //void sendIdentification(Response response);
    }
}