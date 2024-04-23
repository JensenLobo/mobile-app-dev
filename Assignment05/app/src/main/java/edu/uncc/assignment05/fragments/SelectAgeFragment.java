package edu.uncc.assignment05.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.uncc.assignment05.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectAgeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectAgeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectAgeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectAgeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectAgeFragment newInstance(String param1, String param2) {
        SelectAgeFragment fragment = new SelectAgeFragment();
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
        return inflater.inflate(R.layout.fragment_select_age, container, false);
    }
    ArrayAdapter<Integer> adapter;
    ArrayList<Integer> ages = new ArrayList<>();


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        ListView listView = view.findViewById(R.id.listView);

        for(int i = 18; i < 101; i++){
            ages.add(i);
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, ages);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int age = ages.get(position);
                mListener.sendAge(age);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }

    SelectAgeFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (SelectAgeFragmentListener) context;
    }

    public interface SelectAgeFragmentListener{
        void cancel();
        void sendAge(Integer age);
    }
}