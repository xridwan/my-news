package com.xridwan.mynewsapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xridwan.mynewsapp.R;
import com.xridwan.mynewsapp.adapter.NewsAdapter;
import com.xridwan.mynewsapp.models.Article;
import com.xridwan.mynewsapp.presenters.NewsListPresenter;

import java.util.List;

public class NewsListActivity extends AppCompatActivity implements
        INewsListContract.INewsListView, NewsAdapter.ItemClickListener {

    public static final String NEWS = "news";
    public static final String NAME = "name";
    public static final String DESC = "desc";

    TextView tvName, tvDesc;
    ImageView imgBack;

    ProgressDialog progressDialog;
    NewsAdapter listAdapter;
    RecyclerView recyclerView;
    NewsListPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        initViews();

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString(NEWS);
        String name = bundle.getString(NAME);
        String desc = bundle.getString(DESC);

        detailPresenter = new NewsListPresenter(this);
        detailPresenter.onReadNews(id);

        tvName.setText(name);
        tvDesc.setText(desc);
        imgBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void initViews() {
        tvName = findViewById(R.id.tv_name);
        tvDesc = findViewById(R.id.tv_desc);
        imgBack = findViewById(R.id.img_back);
        recyclerView = findViewById(R.id.rv_headlines);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, "Success get data" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, "Failure get data" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowLoading() {
        progressDialog.setMessage("Getting News...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onHideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void onData(List<Article> articleList) {
        listAdapter = new NewsAdapter(NewsListActivity.this, articleList, this);
        listAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClick(String sourceUrl) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.DETAIL, sourceUrl);
        startActivity(intent);
    }
}