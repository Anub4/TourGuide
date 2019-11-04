package np.com.tourguide.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import np.com.tourguide.R;
import np.com.tourguide.adapters.PackageAdapter;
import np.com.tourguide.databinding.ActivityPackageBinding;
import np.com.tourguide.listeners.MyChildEventListener;
import np.com.tourguide.models.Package;
import np.com.tourguide.utils.FirebaseUtils;

public class PackageActivity extends AppCompatActivity {

    private ActivityPackageBinding b;
    private String path, title;
    private List<Package> packageList;
    private PackageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_package);

        path = getIntent().getStringExtra("path");
        title = getIntent().getStringExtra("title");
        b.toolbar.setTitle(title);
        b.toolbar.icBack.setOnClickListener(v->super.onBackPressed());

        packageList = new ArrayList<>();
        adapter = new PackageAdapter(this, packageList);

        b.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        b.recyclerView.setAdapter(adapter);

        b.swiperefresh.setRefreshing(true);
        b.swiperefresh.setOnRefreshListener(() -> getPackageList());
        getPackageList();
    }

    private void getPackageList() {
        packageList.clear();
        FirebaseUtils.database.child(path).addChildEventListener(new MyChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                super.onChildAdded(dataSnapshot, s);
                Package pkg = dataSnapshot.getValue(Package.class);
                pkg.setTitle(dataSnapshot.getKey());
                packageList.add(pkg);
                adapter.notifyDataSetChanged();
                b.swiperefresh.setRefreshing(false);
            }
        });
    }
}
