package com.solfashop.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.solfashop.API.Interfaces.CheckoutService;
import com.solfashop.API.Interfaces.RegisterService;
import com.solfashop.API.ServiceGenerator;
import com.solfashop.API.form.CheckoutForm;
import com.solfashop.API.form.RegisterForm;
import com.solfashop.BaseActivity;
import com.solfashop.fragment.RegisterFragment;
import com.solfashop.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ratri on 10/22/2016.
 */
public class RegisterAdapter {
    Context context;
    BaseActivity activity;
    RegisterForm mRegisterForm;
    RegisterResponse registerResponse;
    BaseActivity baseActivity;


    public RegisterAdapter(BaseActivity activity, RegisterForm registerForm){
        context = activity.getBaseContext();
        this.activity = activity;
        mRegisterForm = registerForm;

    }

    public void initData(){
        System.out.println(mRegisterForm.getUsername());
        RegisterService registerService = ServiceGenerator.testCnc(RegisterService.class);
        Call<RegisterResponse> registerResponseCall = registerService.getRegisterResponse(mRegisterForm);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse resp = response.body();
                System.out.println(resp.getResult()+ resp.getMessage());
                //if(resp.getResult().equals(BaseActivity.SUCCESS)){
                if(response.isSuccess()){
//                        //Toast.makeText(, resp.getMessage(), 6);

                    System.out.println("....................sukses");
                    System.out.println(response.raw().toString());
                    System.out.println(new Gson().toJson(response.body()));
                    System.out.println(resp.getResult()+ resp.getMessage());
                    baseActivity.getBaseActivity().startFragment(BaseActivity.FRAGMENT_LOGIN, "Login FRAGMENT");
//
//                    }

                }
                else System.out.println("....................else");
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                System.out.println("...................."+t.toString());
            }
        });
    }
}
