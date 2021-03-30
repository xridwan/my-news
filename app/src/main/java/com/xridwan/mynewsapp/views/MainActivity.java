package com.xridwan.mynewsapp.views;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xridwan.mynewsapp.R;
import com.xridwan.mynewsapp.adapter.SourceListAdapter;
import com.xridwan.mynewsapp.models.SourceList;
import com.xridwan.mynewsapp.presenters.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        IMainContract.IMainView, SourceListAdapter.ItemClickListener {

    private EditText etSearch;
    private ImageView imgCancel;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    private SourceListAdapter listAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mainPresenter = new MainPresenter(MainActivity.this);
        mainPresenter.onReadSources();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    imgCancel.setVisibility(View.GONE);

                } else {
                    imgCancel.setVisibility(View.VISIBLE);

                    try {
                        listAdapter.getFilter().filter(s);

                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void initViews() {
        imgCancel = findViewById(R.id.img_cancel);
        etSearch = findViewById(R.id.et_search);
        recyclerView = findViewById(R.id.rv_news);
        progressDialog = new ProgressDialog(this);

        imgCancel.setOnClickListener(v -> {
            etSearch.setText("");
            try {
                listAdapter.getFilter().filter(etSearch.getText().toString());

            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        });
    }

    @Override
    public void onData(List<SourceList> sourceLists) {
        listAdapter = new SourceListAdapter(MainActivity.this, sourceLists, this);
        listAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
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
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onHideLoading() {
        progressDialog.dismiss();
    }


    @Override
    public void onItemClick(String sourceId, String sourceName, String sourceDesc) {
        Intent intent = new Intent(this, NewsListActivity.class);
        intent.putExtra(NewsListActivity.NEWS, sourceId);
        intent.putExtra(NewsListActivity.NAME, sourceName);
        intent.putExtra(NewsListActivity.DESC, sourceDesc);
        startActivity(intent);
    }
}