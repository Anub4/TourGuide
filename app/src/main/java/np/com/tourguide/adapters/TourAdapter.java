package np.com.tourguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import np.com.tourguide.R;
import np.com.tourguide.models.Destination;
import np.com.tourguide.models.Tour;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.Holder>{

    private Context context;
    private List<Tour> tourList;

    public TourAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tour, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Tour tour = tourList.get(position);
        holder.imgTour.setImageURI(tour.getImage());
        holder.tvTitle.setText(tour.getTitle());
        holder.tvDesc.setText(tour.getDescription());
        holder.tvNumber.setText(tour.getNumber() + "");
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private SimpleDraweeView imgTour;
        private TextView tvTitle, tvDesc, tvNumber;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgTour = itemView.findViewById(R.id.imgTour);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }
    }
}
