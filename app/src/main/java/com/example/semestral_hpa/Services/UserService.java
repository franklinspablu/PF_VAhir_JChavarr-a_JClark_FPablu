package com.example.semestral_hpa.Services;


import com.example.semestral_hpa.Helpers.EstudiantesResponse;
import com.example.semestral_hpa.Helpers.LoginRequest;
import com.example.semestral_hpa.Helpers.LoginResponse;
import com.example.semestral_hpa.Helpers.RegisterRequest;
import com.example.semestral_hpa.Helpers.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("registrar")
    Call<RegisterResponse> createUser(@Body RegisterRequest registerRequest);

    @GET("estudiantes/grupos/{gaid}")
    Call<List<EstudiantesResponse>> getEstudiantesPorGrupo(@Path("gaId") int gaId);

}
