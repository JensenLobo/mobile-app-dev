package edu.uncc.assignment06.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uncc.assignment06.R;
import edu.uncc.assignment06.models.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String name;
    private String category;
    private String priorityStr;
    private int priority;

    private Task mTask = new Task(name, category, priorityStr, priority);
    public void setCategory(String category){
        this.category = category;
    }
    public void setPriority(String priorityStr){
        this.priorityStr = priorityStr;
    }

    public AddTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTaskFragment newInstance(String param1, String param2) {
        AddTaskFragment fragment = new AddTaskFragment();
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
        return inflater.inflate(R.layout.fragment_add_task, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        EditText editTextName = view.findViewById(R.id.editTextName);
        Button buttonSelectPriority = view.findViewById(R.id.buttonSelectPriority);
        Button buttonSelectCategory = view.findViewById(R.id.buttonSelectCategory);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        TextView textViewPriority = view.findViewById(R.id.textViewPriority);
        TextView textViewCategory = view.findViewById(R.id.textViewCategory);

        textViewCategory.setText(category);
        textViewPriority.setText(priorityStr);
        mTask.setCategory(category);
        mTask.setPriorityStr(priorityStr);

        buttonSelectPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToPriority();
            }
        });

        buttonSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToCategories();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter a name", Toast.LENGTH_LONG).show();
                } else if (textViewPriority.getText() != "" && textViewCategory.getText() != ""){
                    Log.d("TAG1", "onClick: "+ textViewPriority.getText());
                    mTask.setName(name);
                    mListener.addTasksList(mTask);
                    mListener.goToTasks(mTask);
                } else{
                    Toast.makeText(getActivity(), "Please fill out the whole form before proceeding.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    AddTaskFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (AddTaskFragmentListener) context;
    }

    public interface AddTaskFragmentListener{
        void goToTasks(Task task);
        void goToCategories();
        void goToPriority();
        ArrayList<Task> addTasksList(Task task);
    }
}