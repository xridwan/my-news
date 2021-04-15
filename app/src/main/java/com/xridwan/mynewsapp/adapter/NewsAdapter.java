package com.xridwan.mynewsapp.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.xridwan.mynewsapp.R;
import com.xridwan.mynewsapp.models.Article;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {
    private Context context;
    private List<Article> articleList;
    public ItemClickListener itemClickListener;

    public NewsAdapter(Context context, List<Article> articleList,
                       ItemClickListener itemClickListener) {
        this.context = context;
        this.articleList = articleList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_headlines, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Article article = articleList.get(position);

        Animation animation =
                AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.setAnimation(animation);

        try {
            Picasso.get().load(article.getUrlToImage()).into(holder.imgNews);

        } catch (Exception e) {
            Toast.makeText(context,
                    "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        holder.tvTitle.setText(article.getTitle());
        holder.tvDate.setText(article.getPublishedAt());

        holder.itemView.setOnClickListener(v -> {
            itemClickListener.onItemClick(article.getUrl());
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextView tvTitle, tvDate;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.img_news);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }

    public interface ItemClickListener {
        void onItemClick(String sourceUrl);
    }
}
