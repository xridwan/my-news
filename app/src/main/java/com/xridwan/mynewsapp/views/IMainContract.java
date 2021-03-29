package com.xridwan.mynewsapp.views;

import com.xridwan.mynewsapp.models.SourceList;

import java.util.List;

public interface IMainContract {

    interface IMainView {
        void onSuccess(String message);
        void onFailure(String message);
        void onShowLoading();
        void onHideLoading();
        void onData(List<SourceList> sourceLists);
        void initViews();
    }

    interface IMainPresenter {
       void onReadSources();
    }
}
