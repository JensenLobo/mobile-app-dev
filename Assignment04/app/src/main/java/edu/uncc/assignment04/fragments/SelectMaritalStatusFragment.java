package edu.uncc.assignment04.fragments;

import android.os.Bundle;
import android.content.Context;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import edu.uncc.assignment04.R;

public class SelectMaritalStatusFragment extends Fragment {

    public SelectMaritalStatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_marital_status, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maritalStatus= "";
                RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == R.id.radioButtonMarried){
                    maritalStatus = "Married";
                    mListener.sendMaritalStatus(maritalStatus);
                } else if (selectedId == R.id.radioButtonNotMarried) {
                    maritalStatus = "Not Married";
                    mListener.sendMaritalStatus(maritalStatus);
                } else if (selectedId == R.id.radioButtonPreferNotToSay){
                    maritalStatus = "Prefer Not to Say";
                    mListener.sendMaritalStatus(maritalStatus);
                } else {
                    Toast.makeText(getActivity(), "Please select a value!", Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }

    SelectMaritalStatusFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (SelectMaritalStatusFragmentListener) context;
    }

    public interface SelectMaritalStatusFragmentListener{
        void cancel();
        void sendMaritalStatus(String martialStatus);
    }
}