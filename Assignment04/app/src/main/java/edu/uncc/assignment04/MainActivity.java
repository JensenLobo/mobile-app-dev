package edu.uncc.assignment04;
/*
Jensen Lobo
Kareem Abulsoud
Assignment 04
Group 20
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import edu.uncc.assignment04.fragments.DemographicFragment;
import edu.uncc.assignment04.fragments.IdentificationFragment;
import edu.uncc.assignment04.fragments.MainFragment;
import edu.uncc.assignment04.fragments.ProfileFragment;
import edu.uncc.assignment04.fragments.SelectEducationFragment;
import edu.uncc.assignment04.fragments.SelectIncomeFragment;
import edu.uncc.assignment04.fragments.SelectLivingStatusFragment;
import edu.uncc.assignment04.fragments.SelectMaritalStatusFragment;


public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener,
        IdentificationFragment.IdentificationFragmentListener, DemographicFragment.DemographicFragmentListener,
        SelectEducationFragment.SelectEducationFragmentListener, SelectMaritalStatusFragment.SelectMaritalStatusFragmentListener,
        SelectLivingStatusFragment.SelectLivingStatusFragmentListener, SelectIncomeFragment.SelectIncomeFragmentListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new MainFragment())
                .commit();
    }

   @Override
    public void gotoIdentification(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new IdentificationFragment(), "identification-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToDemographic(Response response){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new DemographicFragment(response), "demographic-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToProfile(Response response){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(response), "profile-fragment")
                .addToBackStack(null)
                .commit();
    }

    /*@Override
    public void sendIdentification(Response response){
        //fragment is null.. why?
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("demographic-fragment");
        Log.d("demo1", "sendIdentification: " + fragment);
        if(fragment != null){
            fragment.setIdentification(response);
            Log.d("demo2", "sendIdentification: " + response.getName() + " " + response.getEmail());
        } else{
            fragment = DemographicFragment.newInstance();
            Bundle args = new Bundle();
            args.putSerializable("response", response);
            fragment.setArguments(args);
            Log.d("demo3", "sendIdentification: " + fragment);
            fragment.setIdentification(response);
            Log.d("demo4", "sendIdentification: " + fragment);
        }
        getSupportFragmentManager().popBackStack();

    }*/

    @Override
    public void goToEducation(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectEducationFragment(), "education-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendEducation(String education){
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("demographic-fragment");
        Log.d("demo5", "sendEducation: " + fragment);
        if(fragment != null){
            fragment.setEducation(education);
            Log.d("demo6", "sendEducation: " + education);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToMaritalStatus(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectMaritalStatusFragment(), "marital-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendMaritalStatus(String maritalStatus){
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("demographic-fragment");
        if(fragment != null){
            fragment.setMaritalStatus(maritalStatus);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToLiving(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectLivingStatusFragment(), "living-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendLiving(String living){
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("demographic-fragment");
        if(fragment != null){
            fragment.setLiving(living);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToIncome(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectIncomeFragment(), "income-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendIncome(String income){
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("demographic-fragment");
        if(fragment != null){
            fragment.setIncome(income);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancel(){
        getSupportFragmentManager().popBackStack();
    }

}