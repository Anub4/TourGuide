package np.com.tourguide.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

import np.com.tourguide.R;
import np.com.tourguide.databinding.ActivityEnquireBinding;
import np.com.tourguide.utils.DialogUtils;
import np.com.tourguide.utils.FirebaseUtils;

public class EnquireActivity extends AppCompatActivity {

    private ActivityEnquireBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_enquire);

        b.toolbar.setTitle("Enquiry Form");
        b.toolbar.icBack.setOnClickListener(v -> super.onBackPressed());
    }

    public void submitEnquiry(View view) {
        if (!validate()) return;
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        String fullName = b.etFullName.getText().toString().trim();
        String email = b.etEmail.getText().toString().trim();
        String country = b.spnCountry.getSelectedItem().toString();
        String phoneNumber = b.etPhoneNumber.getText().toString().trim();
        String message = b.etMessage.getText().toString().trim();

        HashMap<String, String> enquiryData = new HashMap<>();
        enquiryData.put("fullName", fullName);
        enquiryData.put("email", email);
        enquiryData.put("country", country);
        enquiryData.put("phoneNumber", phoneNumber);
        enquiryData.put("message", message);

        FirebaseUtils.database.child("enquiry").push().setValue(enquiryData).addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               DialogUtils.showSuccessDialog(this, "Success", "Your enquiry has been submitted successfully!!!");
           }
           dialog.dismiss();
        });
    }

    private boolean validate() {
        if (TextUtils.isEmpty(b.etFullName.getText().toString().trim())) {
            b.etFullName.setError("Full Name is required!!!");
            b.etFullName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(b.etEmail.getText().toString().trim())) {
            b.etEmail.setError("Email is required!!!");
            b.etEmail.requestFocus();
            return false;
        } else if (!b.etEmail.getText().toString().trim().contains("@")) {
            b.etEmail.setError("Invalid email!!!");
            b.etEmail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(b.etPhoneNumber.getText().toString().trim())) {
            b.etPhoneNumber.setError("Phone Number is required!!!");
            b.etPhoneNumber.requestFocus();
            return false;
        } else if (b.etPhoneNumber.getText().toString().trim().length() < 10) {
            b.etPhoneNumber.setError("Phone number must be 10 digit long!!!");
            b.etPhoneNumber.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(b.etMessage.getText().toString().trim())) {
            b.etMessage.setError("Message is required!!!");
            b.etMessage.requestFocus();
            return false;
        }
        return true;
    }
}