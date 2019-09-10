package com.example.tourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import API.API;
//import Reusable.Reusable;
//import Model.LoginSignupResponse;
//import Model.Users;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnRegister, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etloginUsername);
        etPassword = findViewById(R.id.etloginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkUser();
//                //Toast.makeText(LoginActivity.this, "yes", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void checkUser() {
//        API heroesAPI = Reusable.getInstance().create(API.class);
//
//        String username = etUsername.getText().toString();
//        String password = etPassword.getText().toString();
//
//        Users user = new Users(username, password);
//
//        //  Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//
//        Call<LoginSignupResponse> userCall = heroesAPI.loginUser(user);
//
//
//        userCall.enqueue(new Callback<LoginSignupResponse>() {
//            @Override
//            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
//                //Toast.makeText(LoginActivity.this, "Login Successful",Toast.LENGTH_LONG).show();
//
//                if (response.body().getSuccess()){
//                    Toast.makeText(LoginActivity.this, "Login Successful",Toast.LENGTH_LONG).show();
//
////                        Url.Cookie = response.headers().get("Set-Cookie");
//                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(LoginActivity.this, "Invalid username/password",Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Error : "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
}
