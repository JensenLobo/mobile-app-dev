package edu.uncc.evaluation02.fragments;

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
import android.widget.ImageView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.uncc.evaluation02.R;
import edu.uncc.evaluation02.models.Data;
import edu.uncc.evaluation02.models.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TASK = "ARG_TASK";
    private static final String ARG_PARAM2 = "param2";
    String sortBy;

    ListView listView;
    ArrayAdapter<Task> adapter;

    private ArrayList<Task> mTasks = new ArrayList<>();

    public TasksFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TasksFragment newInstance(Task task) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK, task);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Task newtask = (Task) getArguments().getSerializable(ARG_TASK);
            if(newtask != null){
                mListener.addTasksList(newtask);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    ArrayList<Task> sample;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonAddTask = view.findViewById(R.id.buttonAddNew);
        Button buttonClear = view.findViewById(R.id.buttonClearAll);
        ImageView imageViewASC = view.findViewById(R.id.imageViewSortAsc);
        ImageView imageViewDSC = view.findViewById(R.id.imageViewSortDesc);
        listView = view.findViewById(R.id.listView);
        sample = Data.getSampleTestTasks();
        if(!mListener.getAllTasks().isEmpty()){
            sample = mListener.getAllTasks();
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, sample);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddTasks();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sample.clear();
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.gotoTaskDetails(sample.get(position));
            }
        });

        imageViewASC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedASC("priority");
            }
        });

        imageViewDSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortCriteriaSelectedDSC("priority");
            }
        });
    }

    public void sortTasksASC(String criteria) {
        if (criteria.equals("priority")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    Log.d("TAG22", "compare: " + o1.getPriorityStr() + " " + o2.getPriorityStr());
                    return o1.getPriorityStr().compareToIgnoreCase(o2.getPriorityStr());
                }
            });
        }
        adapter.notifyDataSetChanged();
    }

    public void sortTasksDSC(String criteria){
        if (criteria.equals("priority")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    Log.d("TAG22", "compare: " + o1.getPriorityStr() + " " + o2.getPriorityStr());
                    return o2.getPriorityStr().compareToIgnoreCase(o1.getPriorityStr());
                }
            });
        }
        adapter.notifyDataSetChanged();
    }

    TasksFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (TasksFragmentListener) context;
    }

    //TODO: The interface for the TasksFragment
    public interface TasksFragmentListener{
        ArrayList<Task> getAllTasks();
        void gotoAddTasks();
        void gotoTaskDetails(Task task);
        ArrayList<Task> addTasksList(Task task);
        void sortCriteriaSelectedASC(String criteria);
        void sortCriteriaSelectedDSC(String criteria);

    }
}