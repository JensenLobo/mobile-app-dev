package edu.uncc.assignment06.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uncc.assignment06.R;
import edu.uncc.assignment06.models.Data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectPriorityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectPriorityFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;


    public SelectPriorityFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SelectPriorityFragment newInstance(String param1, String param2) {
        SelectPriorityFragment fragment = new SelectPriorityFragment();
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
        return inflater.inflate(R.layout.fragment_select_priority, container, false);
    }

    String[] priorities;
    SelectPriorityAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        recyclerView = view.findViewById(R.id.recyclerView);

        priorities = Data.getPriorities();
        adapter = new SelectPriorityAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }

    SelectPriorityFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (SelectPriorityFragmentListener) context;
    }

    public interface SelectPriorityFragmentListener{
        void cancel();
        void sendSelectedPriority(String priority);
    }

    class SelectPriorityAdapter extends RecyclerView.Adapter<SelectPriorityAdapter.SelectPriorityViewHolder>{
        @NonNull
        @Override
        public SelectPriorityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = getLayoutInflater().inflate(R.layout.selection_list_item, parent, false);
            return new SelectPriorityViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectPriorityViewHolder holder, int position){
            String priority = priorities[position];
            holder.setupUI(priority);
        }

        @Override
        public int getItemCount(){
            return priorities.length;
        }

        class SelectPriorityViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            String mPriority;
            public SelectPriorityViewHolder(@NonNull View itemView){
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.sendSelectedPriority(mPriority);
                    }
                });
            }

            public void setupUI(String priority){
                textView.setText(priority);
                mPriority = priority;
            }
        }
    }
}