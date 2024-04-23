package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.uncc.assignment04.R;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Button buttonStart = view.findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoIdentification();
            }
        });
    }
    MainFragmentListener mListener;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (MainFragmentListener) context;
    }
    public interface MainFragmentListener {
        void gotoIdentification();
    }
}