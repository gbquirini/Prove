package com.example.bui.geocodev3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class MainActivity extends AppCompatActivity {


    public interface Service {
        @GET("json")
        Call<JsonElement> parse(@Query("address") String address);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://maps.googleapis.com/maps/api/geocode/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        // create an instance of the ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // make a request by calling the corresponding method
        Single<Person> person = apiService.getPersonData(personId, API_KEY);
    }


    public static class Location {
        public String street = null;
        public String number = null;
        public String zipCode = null;
        public String description = null;
        public double latitude;
        public double longitude;

        public static Location fromJson(JsonElement jsonElement) {
            Location location = new Location();
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            location.street = jsonObject.get("street").getAsString();
            location.number = jsonObject.get("number").getAsString();
            location.zipCode = jsonObject.get("zipCode").getAsString();
            location.latitude = jsonObject.get("latitude").getAsDouble();
            location.longitude = jsonObject.get("longitude").getAsDouble();
            location.description = jsonObject.get("description").getAsString();

            return location;
        }
        public String toString() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("street", street);
            jsonObject.addProperty("number", number);
            jsonObject.addProperty("zipCode", zipCode);
            jsonObject.addProperty("latitude", latitude);
            jsonObject.addProperty("longitude", longitude);
            jsonObject.addProperty("description", description);

            return jsonObject.toString();
        }

        public String addressLine() {
            return street + ", " + number + " - placemark - " + zipCode;
        }
    }
}


