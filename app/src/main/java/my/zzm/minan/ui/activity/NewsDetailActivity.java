package my.zzm.minan.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import my.zzm.minan.R;
import my.zzm.minan.theme.colorUi.util.SharedPreferencesMgr;
import my.zzm.minan.ui.view.NewsDetailHeaderView;

import static my.zzm.minan.utils.ConstanceValue.ITEM_ID;
import static my.zzm.minan.utils.ConstanceValue.NEWS_SOURCE;
import static my.zzm.minan.utils.ConstanceValue.NEWS_TIME;
import static my.zzm.minan.utils.ConstanceValue.NEWS_TITLE;
import static my.zzm.minan.utils.ConstanceValue.NEWS_URL;

/**
 * Created by AlexanDaking on 17/7/5.
 */

public class NewsDetailActivity extends BaseNewsActivity{
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.share_btn)
    ImageView shareBtn;
    private NewsDetailHeaderView mHeaderView;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_news_detail);
        super.loadViewLayout();
    }
    @Override
    public View createHeader() {
        return mHeaderView = new NewsDetailHeaderView(this);
    }

    @OnClick(R.id.back_btn)
    public void onBackClick(View view) {
        finish();
    }

    @Override
    public void onGetNewsDetailSuccess(String news_title, String news_source, long news_time, String itemId, String news_url, String news_content) {
        Log.i("get news", news_url);
        Log.i("get news", itemId);
        Log.i("get news", news_title);
        Log.i("get news", news_source);
        Log.i("get news", news_time+"");
//        SharedPreferencesMgr.setString(NEWS_TITLE, news_title);
//        SharedPreferencesMgr.setString(NEWS_SOURCE, news_source);
//        SharedPreferencesMgr.setLong(NEWS_TIME, news_time);
//        SharedPreferencesMgr.setString(ITEM_ID, itemId);
//        SharedPreferencesMgr.setString(NEWS_URL, news_url);
        mHeaderView.setDetail(news_title,news_source,news_time,itemId,news_url,news_content);
    }
}