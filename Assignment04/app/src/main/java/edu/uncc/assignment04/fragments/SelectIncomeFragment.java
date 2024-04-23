package edu.uncc.assignment04.fragments;

import android.os.Bundle;
import android.content.Context;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.uncc.assignment04.R;

public class SelectIncomeFragment extends Fragment {

    public SelectIncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_income, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        TextView textViewHouseHoldIncome = view.findViewById(R.id.textViewHouseHoldIncome);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    textViewHouseHoldIncome.setText("<$25K");
                } else if (progress == 1) {
                    textViewHouseHoldIncome.setText(("$25K to <$50K"));
                } else if (progress == 2){
                    textViewHouseHoldIncome.setText("$50K to <$100K");
                } else if (progress == 3){
                    textViewHouseHoldIncome.setText("$100K to <$200K");
                } else if (progress == 4) {
                    textViewHouseHoldIncome.setText(">$200K");
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String income = "";

                if(textViewHouseHoldIncome.getText() == "<$25K"){
                    income = "<$25K";
                } else if (textViewHouseHoldIncome.getText() == "$25K to <$50K") {
                    income = "$25K to <$50K";
                } else if (textViewHouseHoldIncome.getText() == "$50K to <$100K") {
                    income = "$50K to <$100K";
                } else if (textViewHouseHoldIncome.getText() == "$100K to <$200K") {
                    income = "$100K to <$200K";
                } else if (textViewHouseHoldIncome.getText() == ">$200K") {
                    income = ">$200K";
                }
                mListener.sendIncome(income);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }

    SelectIncomeFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (SelectIncomeFragmentListener) context;
    }

    public interface SelectIncomeFragmentListener{
        void cancel();
        void sendIncome(String income);
    }

}