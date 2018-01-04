package my.zzm.minan.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import my.zzm.minan.R;
import my.zzm.minan.theme.colorUi.util.SharedPreferencesMgr;
import my.zzm.minan.utils.DateUtils;

import static my.zzm.minan.utils.ConstanceValue.NEWS_SOURCE;
import static my.zzm.minan.utils.ConstanceValue.NEWS_TIME;
import static my.zzm.minan.utils.ConstanceValue.NEWS_TITLE;
import static my.zzm.minan.utils.ConstanceValue.NEWS_URL;

/**
 * Created by AlexanDaking on 17/7/5.
 */

public class NewsDetailHeaderView extends FrameLayout {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvUserName)
    TextView mTvUserName;
    @BindView(R.id.tvDate)
    TextView mTvDate;
    @BindView(R.id.webView)
    ProgressWebView mWebView;
    @BindView(R.id.tvHtmlContent)
    HtmlTextView mTvHtmlContent;

    public NewsDetailHeaderView(Context context) {
        this(context, null);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.header_news_detail, this);
        ButterKnife.bind(this, this);
    }

    public void setDetail(String news_title, String news_source, long news_time, String itemId, String news_url, String news_content) {
//        news_title = SharedPreferencesMgr.getString(NEWS_TITLE, "");
//        news_source = SharedPreferencesMgr.getString(NEWS_SOURCE, "");
//        news_time = SharedPreferencesMgr.getLong(NEWS_TIME, 0);
//        news_url = SharedPreferencesMgr.getString(NEWS_URL, "");
        mTvTitle.setText(news_title);
        mTvUserName.setText(news_source);
        mTvDate.setText(DateUtils.getShortTime(news_time * 1000));
        mWebView.setVisibility(GONE);
//        if (TextUtils.isEmpty(news_url))
//            mWebView.setVisibility(GONE);
//        mWebView.loadUrl(news_url);
        if (TextUtils.isEmpty(news_content))
            mTvHtmlContent.setVisibility(GONE);
        mTvHtmlContent.setRichText(news_content);
    }
}
