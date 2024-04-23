package edu.uncc.assignment06.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.uncc.assignment06.R;
import edu.uncc.assignment06.models.Data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectCategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;

    public SelectCategoryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SelectCategoryFragment newInstance(String param1, String param2) {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_category, container, false);
    }

    String [] categories;
    SelectCategoryAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        recyclerView = view.findViewById(R.id.recyclerView);

        categories = Data.getCategories();
        adapter = new SelectCategoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }

    SelectCategoryFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (SelectCategoryFragmentListener) context;
    }

    public interface SelectCategoryFragmentListener{
        void cancel();
        void sendSelectedCategory(String category);
    }

    class SelectCategoryAdapter extends RecyclerView.Adapter<SelectCategoryAdapter.SelectCategoryViewHolder>{
        @NonNull
        @Override
        public SelectCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = getLayoutInflater().inflate(R.layout.selection_list_item, parent, false);
            return new SelectCategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectCategoryViewHolder holder, int position){
            String category = categories[position];
            holder.setupUI(category);
        }

        @Override
        public int getItemCount(){
            return categories.length;
        }

        class SelectCategoryViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            String mCategory;
            public SelectCategoryViewHolder(@NonNull View itemView){
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.sendSelectedCategory(mCategory);
                    }
                });
            }

            public void setupUI(String category){
                textView.setText(category);
                mCategory = category;
            }
        }
    }
}