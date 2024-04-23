package edu.uncc.assignment06.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import edu.uncc.assignment06.R;
import edu.uncc.assignment06.models.Data;
import edu.uncc.assignment06.models.Task;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String sortBy;

    RecyclerView recyclerView;

    private ArrayList<Task> mTasks;

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
               // mListener.addTasksList(newtask);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    //ArrayList<Task> tasks;
    TasksAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonAddNew = view.findViewById(R.id.buttonAddNew);
        Button buttonClearAll = view.findViewById(R.id.buttonClearAll);
        ImageView imageViewSortAsc = view.findViewById(R.id.imageViewSortAsc);
        ImageView imageViewSortDesc = view.findViewById(R.id.imageViewSortDesc);
        TextView textViewSortIndicator = view.findViewById(R.id.textViewSortIndicator);
        String sortedASC = "Sorted by Priority (ASC)";
        String sortedDSC = "Sorted by Priority (DESC)";

        recyclerView = view.findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TasksAdapter();
        recyclerView.setAdapter(adapter);

        if(!mListener.getAllTasks().isEmpty()){
            mTasks = mListener.getAllTasks();
        }

        buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddTask();
            }
        });

        buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTasks.clear();
                adapter.notifyDataSetChanged();
            }
        });

        imageViewSortAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSortIndicator.setText(sortedASC);
                mListener.sortCriteriaSelectedASC("priority");
            }
        });

        imageViewSortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSortIndicator.setText(sortedDSC);
                mListener.sortCriteriaSelectedDSC("priority");
            }
        });
    }

    public void sortTasksASC(String criteria) {
        if (criteria.equals("priority")) {
            sortBy = criteria;
            Collections.sort(mTasks, new Comparator<Task>() {
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
            Collections.sort(mTasks, new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    Log.d("TAG22", "compare: " + o1.getPriorityStr() + " " + o2.getPriorityStr());
                    return o2.getPriorityStr().compareToIgnoreCase(o1.getPriorityStr());
                }
            });
        }
        adapter.notifyDataSetChanged();
    }

    TasksListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (TasksListener) context;
    }

    //TODO: The interface for the TasksFragment
    public interface TasksListener{
        ArrayList<Task> getAllTasks();
        void gotoAddTask();
        void goToTaskDetails(Task task);
        ArrayList<Task> addTasksList(Task task);
        ArrayList<Task> delete(Task task);
        void sortCriteriaSelectedASC(String criteria);
        void sortCriteriaSelectedDSC(String criteria);
    }

    class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder>{
        @NonNull
        @Override
        public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = getLayoutInflater().inflate(R.layout.task_list_item, parent, false);
            return new TasksViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TasksViewHolder holder, int position){
            Task task = mTasks.get(position);
            holder.setupUI(task);
        }

        @Override
        public int getItemCount(){
            return mTasks.size();
        }

        class TasksViewHolder extends RecyclerView.ViewHolder{
            Task mTask;
            TextView textViewName, textViewPriority, textViewCategory;
            ImageView imageViewDelete;
            public TasksViewHolder(@NonNull View itemView){
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewPriority = itemView.findViewById(R.id.textViewPriority);
                textViewCategory = itemView.findViewById(R.id.textViewCategory);
                imageViewDelete = itemView.findViewById(R.id.imageView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.goToTaskDetails(mTask);
                    }
                });
            }

            public void setupUI(Task task){
                mTask = task;
                textViewName.setText(mTask.getName());
                textViewPriority.setText(mTask.getPriorityStr());
                textViewCategory.setText(mTask.getCategory());
                imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.delete(mTask);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }
}