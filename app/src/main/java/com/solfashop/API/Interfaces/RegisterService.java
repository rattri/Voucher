package com.solfashop.API.Interfaces;

import com.solfashop.API.form.RegisterForm;
import com.solfashop.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ratri on 10/22/2016.
 */
public interface RegisterService {
    @POST("register.php")
    Call<RegisterResponse>getRegisterResponse(@Body RegisterForm registerForm);
}
