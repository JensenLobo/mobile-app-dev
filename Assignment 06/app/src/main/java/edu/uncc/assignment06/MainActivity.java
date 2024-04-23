package edu.uncc.assignment06;
/*
Jensen Lobo
Group 20
Assignment 6
 */

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import edu.uncc.assignment06.fragments.AddTaskFragment;
import edu.uncc.assignment06.fragments.SelectCategoryFragment;
import edu.uncc.assignment06.fragments.SelectPriorityFragment;
import edu.uncc.assignment06.fragments.TaskDetailsFragment;
import edu.uncc.assignment06.fragments.TasksFragment;
import edu.uncc.assignment06.models.Data;
import edu.uncc.assignment06.models.Task;

public class MainActivity extends AppCompatActivity implements TasksFragment.TasksListener,
        AddTaskFragment.AddTaskFragmentListener, SelectPriorityFragment.SelectPriorityFragmentListener,
        SelectCategoryFragment.SelectCategoryFragmentListener, TaskDetailsFragment.TaskDetailsFragmentListener {

    private ArrayList<Task> mTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTasks.addAll(Data.sampleTestTasks); //adding for testing

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new TasksFragment())
                .commit();
    }

    @Override
    public void gotoAddTask(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddTaskFragment(), "addTask-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToCategories(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectCategoryFragment(), "selectCategory-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToPriority(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectPriorityFragment(), "selectPriority-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToTasks(Task task){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, TasksFragment.newInstance(task),"tasks-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public ArrayList<Task> addTasksList(Task task){
        mTasks.add(task);
        return mTasks;
    }

    @Override
    public ArrayList<Task> getAllTasks(){
        return mTasks;
    }

    @Override
    public void cancel(){
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedPriority(String priority){
        AddTaskFragment fragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag("addTask-fragment");
        if(fragment != null){
            fragment.setPriority(priority);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedCategory(String category){
        AddTaskFragment fragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag("addTask-fragment");
        if(fragment != null){
            fragment.setCategory(category);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToTaskDetails(Task task){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, TaskDetailsFragment.newInstance(task), "taskDetails-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public ArrayList<Task> delete(Task task){
        mTasks.remove(task);
        return mTasks;
    }

    @Override
    public void sortCriteriaSelectedASC(String criteria){
        TasksFragment fragment = (TasksFragment) getSupportFragmentManager().findFragmentByTag("tasks-fragment");
        if(fragment != null){
            fragment.sortTasksASC(criteria);
        }
    }

    @Override
    public void sortCriteriaSelectedDSC(String criteria){
        TasksFragment fragment = (TasksFragment) getSupportFragmentManager().findFragmentByTag("tasks-fragment");
        if(fragment != null){
            fragment.sortTasksDSC(criteria);
        }
    }








}