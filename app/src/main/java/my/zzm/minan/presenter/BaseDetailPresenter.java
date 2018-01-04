package my.zzm.minan.presenter;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.List;

import my.zzm.minan.base.ApiService;
import my.zzm.minan.base.AppClient;
import my.zzm.minan.base.BasePresenter;
import my.zzm.minan.base.SubscriberCallBack;
import my.zzm.minan.model.Comment;
import my.zzm.minan.view.IBaseDetailView;

/**
 * Created by AlexanDaking on 17/7/5.
 */

public class BaseDetailPresenter extends BasePresenter<IBaseDetailView>{
    public BaseDetailPresenter(IBaseDetailView mvpView) {
        super(mvpView);
    }

    public void getComment(String item_id, int pageNow) {
        int offset = (pageNow - 1) * 10;
        Log.i("commentlist", offset+"");
        Log.i("commentlist",pageNow+"");
        addSubscription(AppClient.getApiService().getComment(ApiService.KEY,item_id,"10",offset+""), new SubscriberCallBack<List<Comment>>() {
            @Override
            protected void onSuccess(List<Comment> response) {
                Logger.i("commentist",response.toString());
                mvpView.onGetCommentSuccess(response);
            }

        });
    }

    public void getNewsDetail(String news_title, String news_source, long news_time, String itemId, String news_url,String news_content){

        Log.i("BaseDetailPresenter",news_title);
        mvpView.onGetNewsDetailSuccess(news_title,news_source,news_time,itemId,news_url,news_content);
    }
}
