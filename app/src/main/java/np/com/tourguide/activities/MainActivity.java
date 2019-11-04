package np.com.tourguide.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import np.com.tourguide.R;
import np.com.tourguide.adapters.DestinationAdapter;
import np.com.tourguide.databinding.ActivityMainBinding;
import np.com.tourguide.listeners.MyChildEventListener;
import np.com.tourguide.models.Destination;
import np.com.tourguide.utils.FirebaseUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;
    private List<Destination> destinationList;
    private DestinationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);

        b.toolbar.setTitle("Destinations");

        destinationList = new ArrayList<>();
        adapter = new DestinationAdapter(this, destinationList);
        b.recyclerView.setAdapter(adapter);
        b.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        b.swiperefresh.setRefreshing(true);
        b.swiperefresh.setOnRefreshListener(() -> loadDestinations());
        loadDestinations();

        b.toolbar.icLogout.setOnClickListener(v -> logout());
    }

    private void loadDestinations() {
        destinationList.clear();
        FirebaseUtils.database.child("destinations").addChildEventListener(new MyChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                super.onChildAdded(dataSnapshot, s);
                Destination destination = dataSnapshot.getValue(Destination.class);
                destination.setCountry(dataSnapshot.getKey());
                destination.setPath("destinations/" + dataSnapshot.getKey() + "/tours/");
                destinationList.add(destination);
                adapter.notifyDataSetChanged();
                b.swiperefresh.setRefreshing(false);
            }
        });
    }

    public void logout() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_for_result);
        dialog.setCanceledOnTouchOutside(false);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        Button btnOk = dialog.findViewById(R.id.btnOK);
        Button btnCancle = dialog.findViewById(R.id.btnCancle);
        tvTitle.setText("Are you sure ?");
        tvMessage.setText("You want to logout from your account?");
        btnOk.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
        });
        btnCancle.setOnClickListener(v -> dialog.dismiss());
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lWindowParams);
    }
}