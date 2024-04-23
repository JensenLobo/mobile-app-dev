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
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.PrimitiveIterator;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.Response;


public class DemographicFragment extends Fragment {
    private String education;
    private String maritalStatus;
    private String living;
    private String income;
    private String name;
    private String email;

    private Response mresponse = new Response(name, email, education ,maritalStatus, living, income);

    /*public void setIdentification(Response response){
        this.name = response.getName();
        this.email = response.getEmail();
        mresponse.setName(this.name);
        mresponse.setEmail(this.email);
        Log.d("demo9", "setIdentification: " + this.name + " " + this.email);
        Log.d("demo9.2", "setIdentification: " + mresponse.getName());
    }*/

    public void setEducation(String education){
        this.education = education;
        Log.d("demo10", "setEducation: " + education);
        Log.d("demo10.5", "setEducation: " + this.education);
    }
    public void setMaritalStatus(String maritalStatus){
        this.maritalStatus = maritalStatus;
    }

    public void setLiving(String living) {
        this.living = living;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public DemographicFragment(Response response) {
        // Required empty public constructor
        name = response.getName();
        email = response.getEmail();
        mresponse.setName(name);
        mresponse.setEmail(email);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demographic, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonEdu = view.findViewById(R.id.buttonSelectEducation);
        Button buttonMarital = view.findViewById(R.id.buttonSelectMarital);
        Button buttonLiving = view.findViewById(R.id.buttonSelectLiving);
        Button buttonIncome = view.findViewById(R.id.buttonSelectIncome);
        Button buttonNext = view.findViewById(R.id.buttonNext);
        TextView textViewEducation = view.findViewById(R.id.textViewEducation);
        TextView textViewMarital = view.findViewById(R.id.textViewMaritalStatus);
        TextView textViewLiving = view.findViewById(R.id.textViewLivingStatus);
        TextView textViewIncome = view.findViewById(R.id.textViewIncomeStatus);



        mresponse.setEducation(education);
        mresponse.setMaritalStatus(maritalStatus);
        mresponse.setLivingStatus(living);
        mresponse.setIncome(income);

        textViewEducation.setText(mresponse.getEducation());
        textViewMarital.setText(mresponse.getMaritalStatus());
        textViewLiving.setText(mresponse.getLivingStatus());
        textViewIncome.setText(mresponse.getIncome());

        Log.d("demo11", "onViewCreated: " + name);
        Log.d("demo12", "onViewCreated: " + mresponse.getEducation());

        buttonEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToEducation();
            }
        });

        buttonMarital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToMaritalStatus();
            }
        });

        buttonLiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToLiving();
            }
        });

        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToIncome();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mresponse.getEducation() != null && mresponse.getMaritalStatus() != null
                        && mresponse.getLivingStatus() != null && mresponse.getIncome() != null) {
                    mListener.goToProfile(mresponse);
                } else {
                    Toast.makeText(getActivity(), "Please select all values before proceeding!", Toast.LENGTH_LONG).show();
                }

            }
        });




    }

    DemographicFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (DemographicFragment.DemographicFragmentListener) context;
    }

    public interface DemographicFragmentListener{
        void goToEducation();
        void goToMaritalStatus();
        void goToLiving();
        void goToIncome();
        void goToProfile(Response response);
    }
}