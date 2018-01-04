package my.zzm.minan.presenter;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.List;

import my.zzm.minan.base.ApiService;
import my.zzm.minan.base.AppClient;
import my.zzm.minan.base.BasePresenter;
import my.zzm.minan.base.SubscriberCallBack;
import my.zzm.minan.model.News;
import my.zzm.minan.view.INewsListView;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class NewsListPresenter extends BasePresenter<INewsListView> {
    public NewsListPresenter(INewsListView mvpView) {
        super(mvpView);
    }

    public void getNewsList(String titleCode) {
        addSubscription(AppClient.getApiService().getNews(ApiService.KEY,titleCode), new SubscriberCallBack<List<News>>() {
            @Override
            protected void onSuccess(List<News> response) {
                Logger.i("NewsListPresenter",response.toString());
                mvpView.onGetNewsListSuccess(response);

            }

            @Override
            protected void onError() {
                Log.i("NewsListPresenter","新闻列表读取失败");
                super.onError();
                mvpView.onError();
            }
        });
    }
}
