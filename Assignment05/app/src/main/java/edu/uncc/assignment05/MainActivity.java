package edu.uncc.assignment05;
/*
Jensen Lobo and Kareem Abulsoud
Assignment 5
Group 20
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import edu.uncc.assignment05.fragments.AddUserFragment;
import edu.uncc.assignment05.fragments.SelectAgeFragment;
import edu.uncc.assignment05.fragments.SelectGenderFragment;
import edu.uncc.assignment05.fragments.SelectGroupFragment;
import edu.uncc.assignment05.fragments.SelectSortFragment;
import edu.uncc.assignment05.fragments.SelectStateFragment;
import edu.uncc.assignment05.fragments.UserDetailsFragment;
import edu.uncc.assignment05.fragments.UsersFragment;
import edu.uncc.assignment05.models.Data;
import edu.uncc.assignment05.models.User;

public class MainActivity extends AppCompatActivity implements AddUserFragment.AddUserFragmentListener, UsersFragment.UsersFragmentListener,
        SelectSortFragment.SelectSortFragmentListener, SelectGenderFragment.SelectGenderFragmentListener,
        SelectAgeFragment.SelectAgeFragmentListener,SelectGroupFragment.SelectGroupFragmentListener,
        SelectStateFragment.SelectStateFragmentListener, UserDetailsFragment.UserDetailsFragmentListener{

    private ArrayList<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new UsersFragment())
                .commit();
    }

    @Override
    public void goToAddUsers(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddUserFragment(), "addUser-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToUsers(User user){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, UsersFragment.newInstance(user), "users-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public  void goToSort(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectSortFragment(), "selectSort-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToGender(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectGenderFragment(), "selectGender-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendGender(String gender){
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("addUser-fragment");
        if(fragment != null){
            fragment.setGender(gender);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToAge(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectAgeFragment(), "selectAge-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendAge(Integer age){
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("addUser-fragment");
        if(fragment != null){
            fragment.setAge(age);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToState(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectStateFragment(), "selectState-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendState(String state){
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("addUser-fragment");
        if(fragment != null){
            fragment.setState(state);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToGroup(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectGroupFragment(), "selectGroup-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendGroup(String group){
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("addUser-fragment");
        if(fragment != null){
            fragment.setGroup(group);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToUserDetails(User user){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, UserDetailsFragment.newInstance(user), "userDetails-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void cancel(){
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public ArrayList<User> getUsersList(){
        mUsers = Data.getSampleTestUsers();
        return mUsers;
    }
    @Override
    public ArrayList<User> addUsersList(User user){
        mUsers.add(user);
        return mUsers;
    }

    @Override
    public ArrayList<User> delete(User user){
        mUsers.remove(user);
        return mUsers;
    }

    @Override
    public void sortCriteriaSelected(String criteria){
        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(fragment != null){
            fragment.sortUsersASC(criteria);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sortCriteriaSelectedDSC(String criteria){
        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(fragment != null){
            fragment.sortUserDSC(criteria);
        }
        getSupportFragmentManager().popBackStack();
    }


}