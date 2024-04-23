package edu.uncc.assignment05.fragments;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;

import edu.uncc.assignment05.R;
import edu.uncc.assignment05.models.Data;
import edu.uncc.assignment05.models.User;

public class UsersFragment extends Fragment {
    ListView listView;
    ArrayList<User> user = new ArrayList<>();
    ArrayAdapter<User> adapter;
    String sortBy, ASC, DSC;
    private static final String ARG_USER = "ARG_USER";
    //private User currentUser;

    public UsersFragment() {
        // Required empty public constructor
    }

    public static UsersFragment newInstance(User user){
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            User currentUser = (User) getArguments().getSerializable(ARG_USER);
            if(currentUser != null){
                mListener.addUsersList(currentUser);
                //adapter.notifyDataSetChanged();
                Log.d("TAG2", "onCreate: " + mListener.getUsersList());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    UsersFragmentListener mListener;
    ArrayList<User> sample;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Button buttonClearAll = view.findViewById(R.id.buttonClearAll);
        Button buttonAddNew = view.findViewById(R.id.buttonAddNew);
        Button buttonSort = view.findViewById(R.id.buttonSort);
        TextView textViewSortIndicator = view.findViewById(R.id.textViewSortIndicator);
        listView = view.findViewById(R.id.listView);
        sample = Data.getSampleTestUsers();
        Log.d("TAG3", "onViewCreated: " + mListener.getUsersList());
        if(!mListener.getUsersList().isEmpty()){
            sample = mListener.getUsersList();
        }
        if(sortBy != null){
            textViewSortIndicator.setText("Sort by " + sortBy);
            Log.d("TAG15", "onViewCreated: " + ASC);
        }


        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, sample);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.goToUserDetails(sample.get(position));
            }
        });

        buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sample.clear();
                adapter.notifyDataSetChanged();
            }
        });

        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.getUsersList();
                Log.d("TAG9", "onClick: " + mListener.getUsersList());
                mListener.goToSort();
            }
        });

        buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToAddUsers();
            }
        });
    }


    public void sortUserDSC(String criteria){
        if(criteria.equals("name")){
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o2.getName().compareToIgnoreCase(o1.getName());
                }
            });
        } else if (criteria.equals("email")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o2.getEmail().compareToIgnoreCase(o1.getEmail());
                }
            });
        }else if (criteria.equals("age")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return Integer.compare(o2.getAge(), o1.getAge());
                }
            });
        } else if (criteria.equals("gender")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o2.getGender().compareToIgnoreCase(o1.getGender());
                }
            });
        } else if (criteria.equals("state")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o2.getState().compareToIgnoreCase(o1.getState());
                }
            });
        } else if (criteria.equals("group")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o2.getGroup().compareToIgnoreCase(o1.getGroup());
                }
            });
        }
        adapter.notifyDataSetChanged();
    }


    public void sortUsersASC(String criteria){
        if(criteria.equals("name")){
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    Log.d("TAG22", "compare: " + o1.getName() + " " + o2.getName());
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });
        } else if(criteria.equals("email")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getEmail().compareToIgnoreCase(o2.getEmail());
                }
            });
        } else if (criteria.equals("age")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return Integer.compare(o1.getAge(), o2.getAge());
                }
            });
        }
        else if (criteria.equals("gender")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getGender().compareToIgnoreCase(o2.getGender());
                }
            });
        }
        else if (criteria.equals("state")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getState().compareToIgnoreCase(o2.getState());
                }
            });
        } else if (criteria.equals("group")) {
            sortBy = criteria;
            Collections.sort(sample, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getGroup().compareToIgnoreCase(o2.getGroup());
                }
            });
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListener = (UsersFragmentListener) context;
    }

    public interface UsersFragmentListener{
        void goToAddUsers();
        void goToSort();
        void goToUserDetails(User user);
        ArrayList<User> getUsersList();
        ArrayList<User> addUsersList(User user);
    }
}