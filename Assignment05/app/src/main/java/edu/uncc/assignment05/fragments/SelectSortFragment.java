package edu.uncc.assignment05.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;

import edu.uncc.assignment05.R;

public class SelectSortFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SelectSortFragment() {
        // Required empty public constructor
    }

    public static SelectSortFragment newInstance(String param1, String param2) {
        SelectSortFragment fragment = new SelectSortFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_sort, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        ImageView imageViewNameAscending = view.findViewById(R.id.imageViewNameAscending);
        ImageView imageViewNameDescending = view.findViewById(R.id.imageViewNameDescending);
        ImageView imageViewEmailAscending = view.findViewById(R.id.imageViewEmailAscending);
        ImageView imageViewEmailDescending = view.findViewById(R.id.imageViewEmailDescending);
        ImageView imageViewGenderAscending = view.findViewById(R.id.imageViewGenderAscending);
        ImageView imageViewGenderDescending = view.findViewById(R.id.imageViewGenderDescending);
        ImageView imageViewAgeAscending = view.findViewById(R.id.imageViewAgeAscending);
        ImageView imageViewAgeDescending = view.findViewById(R.id.imageViewAgeDescending);
        ImageView imageViewStateAscending = view.findViewById(R.id.imageViewStateAscending);
        ImageView imageViewStateDescending = view.findViewById(R.id.imageViewStateDescending);
        ImageView imageViewGroupAscending = view.findViewById(R.id.imageViewGroupAscending);
        ImageView imageViewGroupDescending = view.findViewById(R.id.imageViewGroupDescending);

        imageViewNameAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelected("name");
            }
        });

        imageViewNameDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedDSC("name");
            }
        });

        imageViewEmailAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelected("email");
            }
        });

        imageViewEmailDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedDSC("email");
            }
        });

        imageViewGenderAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelected("gender");
            }
        });

        imageViewGenderDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedDSC("gender");
            }
        });

        imageViewAgeAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelected("age");
            }
        });

        imageViewAgeDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedDSC("age");
            }
        });

        imageViewStateAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelected("state");
            }
        });

        imageViewStateDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedDSC("state");
            }
        });

        imageViewGroupAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelected("group");
            }
        });

        imageViewGroupDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedDSC("group");
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }
    SelectSortFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (SelectSortFragmentListener) context;
    }

    public interface SelectSortFragmentListener{
        void cancel();
        void sortCriteriaSelected(String criteria);
        void sortCriteriaSelectedDSC(String criteria);
    }
}