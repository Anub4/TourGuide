package np.com.tourguide.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

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
    private String path, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_tour);

        path = getIntent().getStringExtra("path");
        title = getIntent().getStringExtra("title");
        b.toolbar.setTitle(title);
        b.toolbar.icBack.setOnClickListener(v -> super.onBackPressed());

        tourList = new ArrayList<>();
        adapter = new TourAdapter(this, tourList);

        b.recyclerView.setAdapter(adapter);
        b.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        b.swiperefresh.setRefreshing(true);
        b.swiperefresh.setOnRefreshListener(() -> getTours());
        getTours();
    }

    private void getTours() {
        tourList.clear();
        FirebaseUtils.database.child(path).addChildEventListener(new MyChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                super.onChildAdded(dataSnapshot, s);
                Tour tour = dataSnapshot.getValue(Tour.class);
                tour.setTitle(dataSnapshot.getKey());
                tour.setPath(path + dataSnapshot.getKey() + "/packages");
                tourList.add(tour);
                adapter.notifyDataSetChanged();
                b.swiperefresh.setRefreshing(false);
            }
        });
    }
}