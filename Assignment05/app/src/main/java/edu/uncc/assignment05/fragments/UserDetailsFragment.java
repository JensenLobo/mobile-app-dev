package edu.uncc.assignment05.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.uncc.assignment05.R;
import edu.uncc.assignment05.models.User;


public class UserDetailsFragment extends Fragment {
    private static final String ARG_USER_DETAILS = "ARG_USER_DETAILS";
    ArrayList<User> user = new ArrayList<>();
    private String name;
    private String email;
    private String gender;
    private int age;
    private String state;
    private String group;
    private User mUser = new User(name, email, gender, age, state, group);
    int currentIndex = 0;


    public UserDetailsFragment() {
        // Required empty public constructor
    }

    public static UserDetailsFragment newInstance(User user) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_DETAILS, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(ARG_USER_DETAILS);
            currentIndex = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonback = view.findViewById(R.id.buttonBack);
        ImageView imageViewDelete = view.findViewById(R.id.imageViewDelete);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewGender = view.findViewById(R.id.textViewGender);
        TextView textViewAge = view.findViewById(R.id.textViewAge);
        TextView textViewState = view.findViewById(R.id.textViewState);
        TextView textViewGroup = view.findViewById(R.id.textViewGroup);

        textViewName.setText(mUser.getName());
        textViewEmail.setText(mUser.getEmail());
        textViewGender.setText(mUser.getGender());
        textViewAge.setText(String.valueOf(mUser.getAge()));
        textViewState.setText(mUser.getState());
        textViewGroup.setText(mUser.getGroup());

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST10", "onClick: " + mUser);
                mListener.delete(mUser);
                mListener.cancel();
            }
        });
    }
    UserDetailsFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (UserDetailsFragmentListener) context;
    }

    public interface UserDetailsFragmentListener{
        void cancel();
        ArrayList<User> delete(User user);
    }
}