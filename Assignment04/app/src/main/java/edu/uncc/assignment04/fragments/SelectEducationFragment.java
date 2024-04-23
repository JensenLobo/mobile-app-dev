package edu.uncc.assignment04.fragments;

import android.os.Bundle;
import android.content.Context;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import edu.uncc.assignment04.R;


public class SelectEducationFragment extends Fragment {

    public SelectEducationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_education, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
                String education = "";
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == R.id.radioButtonBHS){
                    education = "Below High School";
                    mListener.sendEducation(education);
                } else if (selectedId == R.id.radioButtonHS) {
                    education = "High School";
                    mListener.sendEducation(education);
                } else if (selectedId == R.id.radioButtonBS) {
                    education = "Bachelor's Degree";
                    mListener.sendEducation(education);
                } else if (selectedId == R.id.radioButtonMS) {
                    education = "Master's Degree";
                    mListener.sendEducation(education);
                } else if (selectedId == R.id.radioButtonPHD) {
                    education = "Ph.D or Higher";
                    mListener.sendEducation(education);
                } else if (selectedId == R.id.radioButtonTS) {
                    education = "Trade School";
                    mListener.sendEducation(education);
                } else if (selectedId == R.id.radioButtonPreferNotToSay) {
                    education = "Prefer not to say";
                    mListener.sendEducation(education);
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
    SelectEducationFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (SelectEducationFragmentListener) context;
    }

    public interface SelectEducationFragmentListener{
        void sendEducation(String education);
        void cancel();
    }

}