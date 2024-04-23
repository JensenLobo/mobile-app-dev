package edu.uncc.evaluation02.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uncc.evaluation02.R;
import edu.uncc.evaluation02.models.Task;

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
    private String category;
    private String name;
    private String priorityStr;
    private int priority;

    private Task mTask = new Task(name, category, priorityStr, priority);

    public void setCategory(String category){
        this.category = category;
    }


    public AddTaskFragment() {
        // Required empty public constructor
    }

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
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
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
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        Button buttonSelect = view.findViewById(R.id.buttonSelectCategory);
        TextView textViewCategory = view.findViewById(R.id.textViewCategory);
        EditText editTextName = view.findViewById(R.id.editTextName);


        textViewCategory.setText(category);
        mTask.setCategory(category);


        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoCategories();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String name = editTextName.getText().toString();
                String priority = "Very High";
                mTask.setName(name);
                Log.d("TAG11", "onClick: " + mTask.getCategory());

                if(selectedId == R.id.radioButtonHigh){
                    priority = "High";

                } else if (selectedId == R.id.radioButtonVeryHigh) {
                    priority = "Very High";

                }else if (selectedId == R.id.radioButtonMedium) {
                    priority = "Medium";

                }else if (selectedId == R.id.radioButtonLow) {
                    priority = "Low";

                }else if (selectedId == R.id.radioButtonVeryLow) {
                    priority = "Very Low";

                }
                mTask.setPriorityStr(priority);

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter a name", Toast.LENGTH_LONG).show();
                } else if (textViewCategory.getText() != null) {
                    Log.d("TAG12", "onClick: "+ mTask);
                    mListener.addTasksList(mTask);
                    mListener.gotoTasks(mTask);
                } else {
                    Toast.makeText(getActivity(), "Please fill out the whole task before proceeding", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    AddTaskFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (AddTaskFragmentListener) context;
    }

    public interface AddTaskFragmentListener{
        void gotoCategories();
        ArrayList<Task> addTasksList(Task task);

        void gotoTasks(Task task);

    }

}