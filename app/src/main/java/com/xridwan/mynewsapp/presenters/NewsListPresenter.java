package com.xridwan.mynewsapp.presenters;

import androidx.annotation.NonNull;

import com.xridwan.mynewsapp.models.Article;
import com.xridwan.mynewsapp.models.Headlines;
import com.xridwan.mynewsapp.utils.Config;
import com.xridwan.mynewsapp.views.INewsListContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListPresenter implements INewsListContract.INewsListPresenter {

    INewsListContract.INewsListView INewsListView;

    public NewsListPresenter(INewsListContract.INewsListView INewsListView) {
        this.INewsListView = INewsListView;
    }

    @Override
    public void onReadNews(String sourcesId) {
        INewsListView.onShowLoading();
        Call<Headlines> getHeadlinesCall =
                Config.apiInterface().getHeadlines(sourcesId, Config.API_KEY);
        getHeadlinesCall.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(@NonNull Call<Headlines> call,
                                   @NonNull Response<Headlines> response) {
                INewsListView.onHideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articleList =
                            response.body().getArticles();
                    INewsListView.onData(articleList);
                    INewsListView.onSuccess(response.message());

                } else {
                    INewsListView.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Headlines> call, @NonNull Throwable t) {
                INewsListView.onHideLoading();
                INewsListView.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
