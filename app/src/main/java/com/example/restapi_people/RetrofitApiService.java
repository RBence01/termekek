package com.example.restapi_people;

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
    @GET("acttFJ/people")
    Call<List<People>> getAllPeople();

    // GET user by ID
    @GET("acttFJ/people/{id}")
    Call<People> getPeopleById(@Path("id") int id);

    // POST (create a new user)
    @POST("acttFJ/people")
    Call<People> createPeople(@Body People people);

    // PUT (update a user)
    @PUT("acttFJ/people/{id}")
    Call<People> updatePeople(@Path("id") int id, @Body People people);

    // DELETE (delete a user by ID)
    @DELETE("acttFJ/people/{id}")
    Call<Void> deletePeople(@Path("id") int id);
}
