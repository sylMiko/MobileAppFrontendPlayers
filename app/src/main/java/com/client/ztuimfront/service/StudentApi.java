package com.client.ztuimfront.service;

import com.client.ztuimfront.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentApi {

    @GET("/student/getAll")
    Call<List<Student>> getAllStudents();

    @POST("/student/save")
    Call<Student> save(@Body Student student);

    @GET("/student/{id}")
    Call<Student> findById(@Path("id") long id);

    @PUT("/student/update/{id}")
    Call<Student> update(@Body Student student, @Path("id") long id);

    @DELETE("/student/delete/{id}")
    Call<Void> delete(@Path("id") long id);
}