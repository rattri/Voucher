package com.solfashop.API.Interfaces;

import com.solfashop.API.form.LoginForm;
import com.solfashop.API.form.RegisterForm;
import com.solfashop.model.LoginResponse;
import com.solfashop.model.RegisterResponse;
import com.solfashop.model.ServerRequest;
import com.solfashop.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ratri on 10/5/2016.
 */
public interface LoginService {
    @POST("login.php")
    Call<LoginResponse>getLoginResponse(@Body LoginForm loginForm);
}
