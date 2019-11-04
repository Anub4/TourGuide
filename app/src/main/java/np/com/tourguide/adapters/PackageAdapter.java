package np.com.tourguide.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import np.com.tourguide.activities.PackageDetailsActivity;
import np.com.tourguide.R;
import np.com.tourguide.models.Package;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.Holder> {

    private Context context;
    private List<Package> tourList;

    public PackageAdapter(Context context, List<Package> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_package, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Package tour = tourList.get(position);
        holder.imgPackage.setImageURI(tour.getImage());
        holder.tvTitle.setText(tour.getTitle());
        holder.tvPrice.setText(tour.getCost());
        holder.tvNumber.setText(tour.getTourDuration());
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, PackageDetailsActivity.class);
            intent.putExtra("package", tour);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private SimpleDraweeView imgPackage;
        private TextView tvTitle, tvPrice, tvNumber;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgPackage = itemView.findViewById(R.id.imgPackage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }
    }
}
