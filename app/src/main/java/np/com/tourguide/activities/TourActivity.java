package np.com.tourguide.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import np.com.tourguide.R;
import np.com.tourguide.adapters.TourAdapter;
import np.com.tourguide.databinding.ActivityTourBinding;
import np.com.tourguide.listeners.MyChildEventListener;
import np.com.tourguide.models.Tour;
import np.com.tourguide.utils.FirebaseUtils;

public class TourActivity extends AppCompatActivity {

    private ActivityTourBinding b;
    private TourAdapter adapter;
    private List<Tour> tourList;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_tour);

        key = getIntent().getStringExtra("key");
        b.toolbar.setTitle(key);
        b.toolbar.icBack.setOnClickListener(v->super.onBackPressed());

        tourList = new ArrayList<>();
        adapter = new TourAdapter(this, tourList);

        b.recyclerView.setAdapter(adapter);
        b.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getTours();
    }

    private void getTours() {
        FirebaseUtils.database.child("destinations").child(key).child("tours").addChildEventListener(new MyChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                super.onChildAdded(dataSnapshot, s);
                Tour tour = dataSnapshot.getValue(Tour.class);
                tour.setTitle(dataSnapshot.getKey());
                tourList.add(tour);
                adapter.notifyDataSetChanged();
            }
        });
    }
}