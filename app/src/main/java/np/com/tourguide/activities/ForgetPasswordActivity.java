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
import com.google.firebase.auth.FirebaseAuth;

import np.com.tourguide.R;
import np.com.tourguide.databinding.ActivityForgetPasswordBinding;
import np.com.tourguide.utils.DialogUtils;

public class ForgetPasswordActivity extends AppCompatActivity {

    private ActivityForgetPasswordBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);

        b.include.setTitle("Reset Password");
        b.include.icBack.setOnClickListener(v -> super.onBackPressed());
    }

    public void reset(View view) {
        if (TextUtils.isEmpty(b.etEmail.getText().toString().trim())) {
            b.etEmail.setError("Email is required!!!");
            b.etEmail.requestFocus();
            return;
        }

        String email = b.etEmail.getText().toString().trim();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Sending reset email...");
        dialog.show();

        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    DialogUtils.showSuccessDialog(ForgetPasswordActivity.this, "Success!!!", "Password reset email has been sent to your email address.");
                } else {
                    DialogUtils.showFailedDialog(ForgetPasswordActivity.this, "Failed!!!", task.getException().getMessage());
                }
                dialog.dismiss();
            }
        });
    }
}
