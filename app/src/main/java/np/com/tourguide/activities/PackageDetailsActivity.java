package np.com.tourguide.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import np.com.tourguide.R;
import np.com.tourguide.databinding.ActivityPackageBinding;
import np.com.tourguide.databinding.ActivityPackageDetailsBinding;
import np.com.tourguide.models.Package;

public class PackageDetailsActivity extends AppCompatActivity {

    private ActivityPackageDetailsBinding b;
    private Package pkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_package_details);

        pkg = (Package) getIntent().getSerializableExtra("package");
        b.setPkg(pkg);
        b.toolbar.setTitle("Package Details");
        b.toolbar.icBack.setOnClickListener(v->super.onBackPressed());
        b.imgPackage.setImageURI(pkg.getImage());
    }

    public void openEquiryForm(View view) {
        startActivity(new Intent(this, EnquireActivity.class));
    }
}
