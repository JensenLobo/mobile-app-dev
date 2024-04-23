package edu.uncc.assignment04.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.Response;

public class ProfileFragment extends Fragment {

    private static final String ARG_PROFILE = "ARG_PROFILE";
    private Response mResponse;
    private String name;
    private String email;
    private String role;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(Response response) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROFILE, response);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mResponse = (Response) getArguments().getSerializable(ARG_PROFILE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewEdu = view.findViewById(R.id.textViewEdu);
        TextView textViewMartialStatus = view.findViewById(R.id.textViewMaritalStatus);
        TextView textViewIncome = view.findViewById(R.id.textViewIncomeValue);
        TextView textviewLiving = view.findViewById(R.id.textViewLivingStatus);

        textViewName.setText(mResponse.getName());
        textViewEmail.setText(mResponse.getEmail());
        textViewEdu.setText(mResponse.getEducation());
        textViewMartialStatus.setText(mResponse.getMaritalStatus());
        textviewLiving.setText(mResponse.getLivingStatus());
        textViewIncome.setText(mResponse.getIncome());

    }
}