package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;

import edu.uncc.assignment04.R;


public class SelectLivingStatusFragment extends Fragment {

    public SelectLivingStatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_living_status, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
                String living = "";
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == R.id.radioButtonHomeOwner){
                    living = "Home Owner";
                    mListener.sendLiving(living);
                } else if (selectedId == R.id.radioButtonRenter) {
                    living = "Renter";
                    mListener.sendLiving(living);
                } else if (selectedId == R.id.radioButtonLessee) {
                    living = "Lessee";
                    mListener.sendLiving(living);
                } else if (selectedId == R.id.radioButtonOther) {
                    living = "Other";
                    mListener.sendLiving(living);
                } else if (selectedId == R.id.radioButtonPreferNotToSay) {
                    living = "Prefer Not to Say";
                    mListener.sendLiving(living);
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

    SelectLivingStatusFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (SelectLivingStatusFragmentListener) context;
    }

    public interface SelectLivingStatusFragmentListener{
        void cancel();
        void sendLiving(String living);
    }
}