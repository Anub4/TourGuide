package np.com.tourguide.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import np.com.tourguide.R;
import np.com.tourguide.databinding.ActivityRegisterBinding;
import np.com.tourguide.models.User;
import np.com.tourguide.utils.DialogUtils;


public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding b;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void openLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void register(View view) {
        if (!validate()) return;
        String email = b.etEmail.getText().toString().trim();
        String fullName = b.etFullName.getText().toString().trim();
        String country = b.etCountry.getText().toString().trim();
        String phoneNumber = b.etPhoneNumber.getText().toString().trim();
        String password = b.etPassword.getText().toString().trim();

        User user = new User();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setCountry(country);
        user.setPhoneNumber(phoneNumber);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                auth.getCurrentUser().sendEmailVerification();
                databaseReference.child(auth.getCurrentUser().getUid()).setValue(user);
                DialogUtils.showSuccessDialog(RegisterActivity.this, "Success!!!", "Please check your email to verify your account.");
            } else {
                DialogUtils.showFailedDialog(RegisterActivity.this, "Failed!!!", task.getException().getMessage());
            }
            dialog.dismiss();
        });
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
        } else if (TextUtils.isEmpty(b.etFullName.getText().toString().trim())) {
            b.etFullName.setError("Full Name is required!!!");
            b.etFullName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(b.etCountry.getText().toString().trim())) {
            b.etCountry.setError("Country is required!!!");
            b.etCountry.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(b.etPhoneNumber.getText().toString().trim())) {
            b.etPhoneNumber.setError("Phone Number is required!!!");
            b.etPhoneNumber.requestFocus();
            return false;
        } else if (b.etPhoneNumber.getText().toString().trim().length() < 10) {
            b.etPhoneNumber.setError("Phone number must be 10 digit long!!!");
            b.etPhoneNumber.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(b.etPassword.getText().toString().trim())) {
            b.etPassword.setError("Password is required!!!");
            b.etPassword.requestFocus();
            return false;
        } else if (!b.etPassword.getText().toString().trim().equals(b.etConfirmPassword.getText().toString().trim())) {
            b.etConfirmPassword.setError("Confirm password does not match!!!");
            b.etConfirmPassword.requestFocus();
            return false;
        }
        return true;
    }
}
