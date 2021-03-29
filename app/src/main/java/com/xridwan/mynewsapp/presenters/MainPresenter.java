package com.xridwan.mynewsapp.presenters;

import androidx.annotation.NonNull;

import com.xridwan.mynewsapp.utils.Config;
import com.xridwan.mynewsapp.models.GetSources;
import com.xridwan.mynewsapp.models.SourceList;
import com.xridwan.mynewsapp.views.IMainContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainContract.IMainPresenter {
    IMainContract.IMainView IMainView;

    public MainPresenter(IMainContract.IMainView IMainView) {
        this.IMainView = IMainView;
    }

    @Override
    public void onReadSources() {
        IMainView.onShowLoading();
        Call<GetSources> getSourcesCall = Config.apiInterface().getSources(Config.API_KEY);
        getSourcesCall.enqueue(new Callback<GetSources>() {
            @Override
            public void onResponse(@NonNull Call<GetSources> call,
                                   @NonNull Response<GetSources> response) {
                IMainView.onHideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    List<SourceList> sourceLists =
                            response.body().getSourceLists();
                    IMainView.onData(sourceLists);
                    IMainView.onSuccess(response.message());

                } else {
                    IMainView.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetSources> call, @NonNull Throwable t) {
                IMainView.onHideLoading();
                IMainView.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
