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

import np.com.tourguide.R;
import np.com.tourguide.activities.TourActivity;
import np.com.tourguide.models.Destination;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.Holder> {

    private Context context;
    private List<Destination> destinationList;

    public DestinationAdapter(Context context, List<Destination> destinationList) {
        this.context = context;
        this.destinationList = destinationList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_destination, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Destination destination = destinationList.get(position);
        holder.tvCountry.setText(destination.getCountry());
        holder.imgDestination.setImageURI(destination.getImage());
        holder.itemView.setOnClickListener(v->context.startActivity(new Intent(context, TourActivity.class).putExtra("key", destination.getCountry())));
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private SimpleDraweeView imgDestination;
        private TextView tvCountry;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgDestination = itemView.findViewById(R.id.imgDestination);
            tvCountry = itemView.findViewById(R.id.tvCountry);
        }
    }
}
