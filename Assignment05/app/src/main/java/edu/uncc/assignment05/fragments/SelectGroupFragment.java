package edu.uncc.assignment05.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import edu.uncc.assignment05.R;
import edu.uncc.assignment05.models.Data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectGroupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectGroupFragment newInstance(String param1, String param2) {
        SelectGroupFragment fragment = new SelectGroupFragment();
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
        return inflater.inflate(R.layout.fragment_select_group, container, false);
    }

    ArrayAdapter<String> adapter;
    String[] groups;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        ListView listView = view.findViewById(R.id.listView);
        groups = Data.getGroups();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, groups);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String group = groups[position];
                mListener.sendGroup(group);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }

    SelectGroupFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (SelectGroupFragmentListener) context;
    }

    public interface SelectGroupFragmentListener{
        void cancel();
        void sendGroup(String group);
    }
}