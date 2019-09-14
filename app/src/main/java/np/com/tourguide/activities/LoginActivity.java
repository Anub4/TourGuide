package np.com.tourguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import np.com.tourguide.R;
import np.com.tourguide.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    public void openRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view) {
        if (!validate()) return;
    }

    public void openForgetPassword(View view) {

    }

    private boolean validate() {
        if (TextUtils.isEmpty(b.etEmail.getText().toString().trim())) {
            b.etEmail.setError("Email is required!!!");
            b.etEmail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(b.etPassword.getText().toString().trim())) {
            b.etPassword.setError("Password is required!!!");
            b.etPassword.requestFocus();
            return false;
        }
        return true;
    }
}