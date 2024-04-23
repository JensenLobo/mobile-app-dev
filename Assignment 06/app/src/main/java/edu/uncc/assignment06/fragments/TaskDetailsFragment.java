package edu.uncc.assignment06.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uncc.assignment06.R;
import edu.uncc.assignment06.models.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TASK_DETAILS = "ARG_TASK_DETAILS";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String category;
    private String name;
    private String priorityStr;
    private int priority;

    private Task mTask = new Task(name, category, priorityStr, priority);
    private int currentIndex = 0;

    public TaskDetailsFragment() {
        // Required empty public constructor
    }

    public static TaskDetailsFragment newInstance(Task task) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_DETAILS, task);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTask = (Task) getArguments().getSerializable(ARG_TASK_DETAILS);
            currentIndex = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewCategory = view.findViewById(R.id.textViewCategory);
        TextView textViewPriority = view.findViewById(R.id.textViewPriority);
        ImageView imageViewDelete = view.findViewById(R.id.imageViewDelete);
        Button buttonBack = view.findViewById(R.id.buttonBack);

        textViewName.setText(mTask.getName());
        textViewCategory.setText(mTask.getCategory());
        textViewPriority.setText(mTask.getPriorityStr());

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.delete(mTask);
                mListener.cancel();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });

    }

    TaskDetailsFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (TaskDetailsFragmentListener) context;
    }

    public interface TaskDetailsFragmentListener{
        ArrayList<Task> delete(Task task);
        void cancel();
    }
}