package com.xridwan.mynewsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xridwan.mynewsapp.R;
import com.xridwan.mynewsapp.models.SourceList;

import java.util.List;

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.viewHolder>
        implements Filterable {

    private final Context context;
    public List<SourceList> sourceLists, filterLists;
    private FilterAdapter filterAdapter;
    public ItemClickListener itemClickListener;

    public SourceListAdapter(Context context,
                             List<SourceList> sourceLists,
                             ItemClickListener itemClickListener) {
        this.context = context;
        this.sourceLists = sourceLists;
        this.filterLists = sourceLists;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_source_list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        SourceList sourceList = sourceLists.get(position);

        Animation animation =
                AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);

        String id = sourceList.getId();
        holder.itemView.setAnimation(animation);
        holder.tvName.setText(sourceList.getName());
        holder.tvDesc.setText(sourceList.getDescription());
        holder.tvCategory.setText(sourceList.getCategory());
        holder.tvLanguage.setText(sourceList.getLanguage());
        holder.tvCountry.setText(sourceList.getCountry());

        holder.itemView.setOnClickListener(v -> {
            itemClickListener.onItemClick(id, sourceList.getName(), sourceList.getDescription());
        });
    }

    @Override
    public int getItemCount() {
        return sourceLists.size();
    }

    @Override
    public Filter getFilter() {
        if (filterAdapter == null) {
            filterAdapter = new FilterAdapter(this, filterLists);
        }
        return filterAdapter;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc, tvCategory, tvLanguage, tvCountry;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvLanguage = itemView.findViewById(R.id.tv_language);
            tvCountry = itemView.findViewById(R.id.tv_country);
        }
    }

    public interface ItemClickListener {
        void onItemClick(String sourceId, String sourceName, String sourceDesc);
    }
}
