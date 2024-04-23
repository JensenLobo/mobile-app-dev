package edu.uncc.evaluation02;
//Jensen Lobo
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import edu.uncc.evaluation02.fragments.AddTaskFragment;
import edu.uncc.evaluation02.fragments.SelectCategoryFragment;
import edu.uncc.evaluation02.fragments.TaskDetailsFragment;
import edu.uncc.evaluation02.fragments.TasksFragment;
import edu.uncc.evaluation02.models.Data;
import edu.uncc.evaluation02.models.Task;

public class MainActivity extends AppCompatActivity implements TasksFragment.TasksFragmentListener,
        AddTaskFragment.AddTaskFragmentListener, SelectCategoryFragment.SelectCategoryFragmentListener,
        TaskDetailsFragment.TaskDetailsFragmentListener {

    private ArrayList<Task> mTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTasks.addAll(Data.sampleTestTasks); //adding for testing

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new TasksFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoAddTasks(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddTaskFragment(), "addTask-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        mTasks = Data.getSampleTestTasks();
        return mTasks;
    }

    @Override
    public void gotoTaskDetails(Task task){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, TaskDetailsFragment.newInstance(task), "taskDetails-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoCategories(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectCategoryFragment(), "selectCategory-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendCategory(String category){
        AddTaskFragment fragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag("addTask-fragment");
        if(fragment != null){
            fragment.setCategory(category);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public ArrayList<Task> addTasksList(Task task){
        mTasks.add(task);
        return mTasks;
    }

    @Override
    public void gotoTasks(Task task){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, TasksFragment.newInstance(task), "tasks-fragment")
                .addToBackStack(null)
                .commit();
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

    @Override
    public ArrayList<Task> delete(Task task){
        mTasks.remove(task);
        return mTasks;
    }

    @Override
    public void cancel(){
        getSupportFragmentManager().popBackStack();
    }



}