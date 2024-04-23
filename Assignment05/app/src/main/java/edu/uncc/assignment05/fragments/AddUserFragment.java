package edu.uncc.assignment05.fragments;

import android.icu.util.BuddhistCalendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uncc.assignment05.R;
import edu.uncc.assignment05.models.User;

public class AddUserFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String name;
    private String email;
    private String gender;
    private int age;
    private String state;
    private String group;

    private User mUser = new User(name, email, gender, age, state, group);

    public AddUserFragment() {
        // Required empty public constructor
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public static AddUserFragment newInstance(String param1) {
        AddUserFragment fragment = new AddUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user, container, false);
    }

    @Override
    public  void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        EditText editTextName = view.findViewById(R.id.editTextName);
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        TextView textViewGender = view.findViewById(R.id.textViewGender);
        TextView textViewAge = view.findViewById(R.id.textViewAge);
        TextView textViewState = view.findViewById(R.id.textViewState);
        TextView textViewGroup = view.findViewById(R.id.textViewGroup);
        Button buttonSelectGender = view.findViewById(R.id.buttonSelectGender);
        Button buttonSelectAge = view.findViewById(R.id.buttonSelectAge);
        Button buttonSelectState = view.findViewById(R.id.buttonSelectState);
        Button buttonSelectGroup = view.findViewById(R.id.buttonSelectGroup);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);

        mUser.setGender(gender);
        mUser.setAge(age);
        mUser.setState(state);
        mUser.setGroup(group);


        textViewGender.setText(mUser.getGender());
        textViewAge.setText(String.valueOf(mUser.getAge()));
        textViewState.setText(mUser.getState());
        textViewGroup.setText(mUser.getGroup());

        buttonSelectGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToGender();
            }
        });

        buttonSelectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToAge();
            }
        });

        buttonSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToState();
            }
        });

        buttonSelectGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToGroup();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                mUser.setName(name);
                mUser.setEmail(email);

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter a Name", Toast.LENGTH_LONG).show();
                } else if (email.isEmpty()){
                    Toast.makeText(getActivity(), "Enter an Email", Toast.LENGTH_LONG).show();
                } else if(mUser.getState() != null && mUser.getGroup() != null
                        && mUser.getGender() != null && mUser.getAge() != 0){
                    mListener.addUsersList(mUser);
                    mListener.goToUsers(mUser);
                } else {
                    Toast.makeText(getActivity(), "Please fill out the form completely", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    AddUserFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (AddUserFragmentListener) context;
    }

    public interface AddUserFragmentListener {
        void goToGender();
        void goToAge();
        void goToState();
        void goToGroup();
        void goToUsers(User user);
        ArrayList<User> addUsersList(User user);
    }
}