package edu.uncc.weatherapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import edu.uncc.weatherapp.models.City;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import edu.uncc.weatherapp.R;
import edu.uncc.weatherapp.databinding.FragmentCitiesBinding;
import okhttp3.ResponseBody;

public class CitiesFragment extends Fragment {
    public CitiesFragment() {
        // Required empty public constructor
    }

    FragmentCitiesBinding binding;
    ArrayList<City> cities = new ArrayList<>();
    ArrayAdapter<City> adapter;
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cities);
        binding.listView.setAdapter(adapter);
        getCities();
        Log.d("TAG", "onViewCreated: " + cities);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.gotoWeather(adapter.getItem(position));
            }
        });

    }

    void getCities(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/cities")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();

                    try {
                        JSONObject rootJson = new JSONObject(responseBody);
                        JSONArray citiesJsonArray = rootJson.getJSONArray("cities");
                        cities.clear();

                        for (int i = 0; i < citiesJsonArray.length(); i++) {
                            JSONObject cityJsonObject = citiesJsonArray.getJSONObject(i);
                            String cityName = cityJsonObject.getString("name");
                            String stateName = cityJsonObject.getString("state");
                            Double lat = cityJsonObject.getDouble("lat");
                            Double lng = cityJsonObject.getDouble("lng");
                            City city = new City(cityName, stateName, lng, lat);

                            cities.add(city);
                            Log.d("TAG4", "onResponse: " + city);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        Log.e("TAG1", "JSON parsing error " + e.getMessage() );
                        throw new RuntimeException(e);
                    }

                } else {
                    Log.e("TAG2", "Failed to fetch cities " + response.code() );
                }
            }
        });
    }

    CitiesFragmentListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (CitiesFragmentListener) context;
    }

    public interface CitiesFragmentListener{
        void gotoWeather(City city);
    }
}