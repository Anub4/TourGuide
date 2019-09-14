package np.com.tourguide.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import np.com.tourguide.R;
import np.com.tourguide.databinding.ActivityLoginBinding;
import np.com.tourguide.utils.DialogUtils;
import np.com.tourguide.utils.SharedUtils;

import static np.com.tourguide.utils.FirebaseUtils.auth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_login);
        if (auth.getCurrentUser() != null && auth.getCurrentUser().isEmailVerified()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void openRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view) {
        if (!validate()) return;
        String email = b.etEmail.getText().toString().trim();
        String password = b.etPassword.getText().toString().trim();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in...");
        dialog.show();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (auth.getCurrentUser().isEmailVerified()) {
                    SharedUtils.setString(LoginActivity.this, "uid", auth.getCurrentUser().getUid());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    DialogUtils.showFailedDialog(LoginActivity.this, "Failed!!!", "Please verify your account before login.");
                }
            } else {
                DialogUtils.showFailedDialog(LoginActivity.this, "Failed!!!", task.getException().getMessage());
            }
            dialog.dismiss();
        });

    }

    public void openForgetPassword(View view) {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
    }

    private boolean validate() {
        if (TextUtils.isEmpty(b.etEmail.getText().toString().trim())) {
            b.etEmail.setError("Email is required!!!");
            b.etEmail.requestFocus();
            return false;
        } else if (!b.etEmail.getText().toString().trim().contains("@")) {
            b.etEmail.setError("Invalid email!!!");
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