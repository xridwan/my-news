package com.xridwan.mynewsapp.views;

import com.xridwan.mynewsapp.models.Article;

import java.util.List;

public interface INewsListContract {

    interface INewsListView {
        void onSuccess(String message);
        void onFailure(String message);
        void onShowLoading();
        void onHideLoading();
        void onData(List<Article> articleList);
        void initViews();
    }

    interface INewsListPresenter {
        void onReadNews(String sourcesId);
    }
}
