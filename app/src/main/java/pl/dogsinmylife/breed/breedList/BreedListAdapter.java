package pl.dogsinmylife.breed.breedList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.dogsinmylife.R;
import pl.dogsinmylife.models.Breed;


public class BreedListAdapter extends RecyclerView.Adapter<BreedListAdapter.ViewHolder> {

    List<Breed> breedList;
    BreedItemListener breedItemListener;

    public BreedListAdapter(List<Breed> breedList, BreedItemListener breedItemListener) {
        if (breedList == null) {
            breedList = new ArrayList<>();
        }
        this.breedList = breedList;
        this.breedItemListener = breedItemListener;
    }

    public void updateData(List<Breed> breedList) {
        if (breedList == null) {
            breedList = new ArrayList<>();
        }
        this.breedList.clear();
        this.breedList.addAll(breedList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Breed breed = breedList.get(position);

        holder.nameTv.setText(breed.getName());
        if (breed.isLiked()) {
            holder.iconIv.setImageResource(R.drawable.icon_minus);
        } else {
            holder.iconIv.setImageResource(R.drawable.icon_plus);
        }

        holder.iconIv.setOnClickListener(v -> breedItemListener.onItemButtonClicked(breed));
    }

    @Override
    public int getItemCount() {
        return breedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private ImageView iconIv;

        public ViewHolder(View view) {
            super(view);
            nameTv = (TextView) view.findViewById(R.id.item_breed_name);
            iconIv = (ImageView) view.findViewById(R.id.item_breed_icon);
        }
    }
}

