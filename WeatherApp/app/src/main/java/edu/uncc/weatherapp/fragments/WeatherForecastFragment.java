package edu.uncc.weatherapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.uncc.weatherapp.R;
import edu.uncc.weatherapp.databinding.ForecastListItemBinding;
import edu.uncc.weatherapp.databinding.FragmentWeatherForecastBinding;
import edu.uncc.weatherapp.models.City;
import edu.uncc.weatherapp.models.Forecast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.squareup.picasso.Picasso;



public class WeatherForecastFragment extends Fragment {
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private City mCity;

    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    public static WeatherForecastFragment newInstance(City city) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (City) getArguments().getSerializable(ARG_PARAM_CITY);
        }
    }

    FragmentWeatherForecastBinding binding;
    WeatherForecastAdapter adapter;
    ArrayList<Forecast> forecasts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherForecastBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TextView textViewCityName = view.findViewById(R.id.textViewCityName);
        textViewCityName.setText(mCity.getName() + ", " + mCity.getState());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WeatherForecastAdapter();
        recyclerView.setAdapter(adapter);
        getForecast();
    }



    public void getForecast(){
        DecimalFormat numberFormat = new DecimalFormat("#.0000");
        String lat = numberFormat.format(mCity.getLat());
        String lng = numberFormat.format(mCity.getLng());
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse("https://api.weather.gov/points/").newBuilder()
                .addEncodedPathSegment(lat + "," + lng)
                .build();
        Log.d("TAG8", "getForecast: " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String body = response.body().string();
                    try{
                        JSONObject rootJson = new JSONObject(body);
                        JSONObject properties = rootJson.getJSONObject("properties");
                        String forecastURL = properties.optString("forecast");
                        Log.d("TAG9", "onResponse: " + forecastURL);

                        if(forecastURL != null){
                            OkHttpClient forecastClient = new OkHttpClient();
                            Request forecastRequest = new Request.Builder()
                                    .url(forecastURL)
                                    .build();
                            forecastClient.newCall(forecastRequest).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    if(response.isSuccessful()){
                                        String forecastBody = response.body().string();
                                        try{
                                            JSONObject forecastJson = new JSONObject(forecastBody);
                                            JSONArray periods = forecastJson.getJSONObject("properties").getJSONArray("periods");


                                            for(int i=0; i < periods.length(); i++){
                                                JSONObject period = periods.getJSONObject(i);
                                                String dateTime = period.getString("startTime");
                                                String windSpeed = period.getString("windSpeed");
                                                Double temperature = period.getDouble("temperature");
                                                String shortForecast = period.getString("shortForecast");
                                                JSONObject relativeHumidity = period.getJSONObject("relativeHumidity");
                                                int humidityValue = relativeHumidity.getInt("value");
                                                //String relativeHumidity = period.getString("relativeHumidity");
                                                String icon = period.getString("icon");
                                                Forecast forecast = new Forecast(dateTime, windSpeed, temperature, shortForecast, humidityValue, icon);
                                                forecasts.add(forecast);
                                                Log.d("TAG10", "onResponse: " + forecasts);
                                            }

                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    adapter.notifyDataSetChanged();
                                                }
                                            });
                                        } catch (JSONException e){
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            });

                        }

                    } catch(JSONException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastViewHolder>{
        @NonNull
        @Override
        public WeatherForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = getLayoutInflater().inflate(R.layout.forecast_list_item, parent, false);
            return new WeatherForecastViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WeatherForecastViewHolder holder, int position){
            Forecast forecast = forecasts.get(position);
            holder.setupUI(forecast);
        }

        @Override
        public int getItemCount(){
            return forecasts.size();
        }

        class WeatherForecastViewHolder extends RecyclerView.ViewHolder{
            TextView textViewDateTime, textViewTemperature, textViewHumidity, textViewForecast, textViewWindSpeed;
            Forecast mForecast;

            ImageView imageView;
            public WeatherForecastViewHolder(@NonNull View itemView){
                super(itemView);
                textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
                textViewTemperature = itemView.findViewById(R.id.textViewTemperature);
                textViewHumidity = itemView.findViewById(R.id.textViewHumidity);
                textViewForecast = itemView.findViewById(R.id.textViewForecast);
                textViewWindSpeed = itemView.findViewById(R.id.textViewWindSpeed);
                imageView = itemView.findViewById(R.id.imageView);
            }

            public void setupUI(Forecast forecast){

                mForecast = forecast;
                textViewDateTime.setText(mForecast.getStartTime());
                textViewTemperature.setText(mForecast.getTemp() + " F");
                textViewHumidity.setText("Humidity: " + mForecast.getHumidity() + " %");
                textViewForecast.setText(mForecast.getShortForecast());
                textViewWindSpeed.setText("Wind Speed: " + mForecast.getWindSpeed());

                Picasso.get().load(mForecast.getIcon()).into(imageView);

            }
        }
    }
}

/* post request with api
void deleteContact(String cid){
        RequestBody formBody = new FormBody.Builder()
                .add("id", cid)
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/json/delete")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getContacts();
                        }
                    });
                } else {
                    String body = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        String message = jsonObject.getString("message");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
    }
 */