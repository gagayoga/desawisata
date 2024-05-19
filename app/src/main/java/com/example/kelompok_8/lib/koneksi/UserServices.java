package com.example.kelompok_8.lib.koneksi;

import com.example.kelompok_8.lib.data.login.LoginRequest;
import com.example.kelompok_8.lib.data.login.LoginResponse;
import com.example.kelompok_8.lib.data.register.RegisterRequest;
import com.example.kelompok_8.lib.data.register.RegisterResponse;
import com.example.kelompok_8.lib.data.resetpassword.ResetPasswordRequest;
import com.example.kelompok_8.lib.data.resetpassword.ResetPasswordResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserServices {

    @Headers({"Accept: application/json"})

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("registrasi")
    Call<RegisterResponse> postRegister(@Body RegisterRequest registerRequest);

    @POST("reset-password")
    Call<ResetPasswordResponse> postResetPassword(@Body ResetPasswordRequest resetPasswordRequest);
}
