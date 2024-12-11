package com.example.restapi_people;

import com.example.restapi_people.Termek;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApiService {

    // GET all users
    @GET("/DqmdaH/bevasarlas")
    Call<List<Termek>> getAllTermek();

    // GET user by ID
    @GET("/DqmdaH/bevasarlas/{id}")
    Call<Termek> getTermekById(@Path("id") int id);

    // POST (create a new user)
    @POST("/DqmdaH/bevasarlas")
    Call<Termek> createTermek(@Body Termek Termek);

    // PUT (update a user)
    @PUT("/DqmdaH/bevasarlas/{id}")
    Call<Termek> updateTermek(@Path("id") int id, @Body Termek Termek);

    // DELETE (delete a user by ID)
    @DELETE("/DqmdaH/bevasarlas/{id}")
    Call<Void> deleteTermek(@Path("id") int id);
}
