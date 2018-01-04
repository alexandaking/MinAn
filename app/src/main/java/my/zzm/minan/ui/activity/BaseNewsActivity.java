package my.zzm.minan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseMvpActivity;
import my.zzm.minan.model.Comment;
import my.zzm.minan.model.News;
import my.zzm.minan.presenter.BaseDetailPresenter;
import my.zzm.minan.ui.adapter.CommentAdapter;
import my.zzm.minan.utils.CommonUtil;
import my.zzm.minan.utils.ConstanceValue;
import my.zzm.minan.view.IBaseDetailView;

/**
 * Created by AlexanDaking on 17/7/5.
 */

public abstract class BaseNewsActivity extends BaseMvpActivity<BaseDetailPresenter> implements IBaseDetailView {
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.write_comment_layout)
    TextView writeCommentLayout;
    @BindView(R.id.action_view_up)
    ImageView actionViewUp;
    @BindView(R.id.action_view_comment)
    ImageView actionViewComment;
    @BindView(R.id.action_commont_layout)
    FrameLayout actionCommontLayout;
    @BindView(R.id.action_comment_count)
    TextView actionCommentCount;
    @BindView(R.id.view_comment_layout)
    FrameLayout viewCommentLayout;
    @BindView(R.id.action_favor)
    ImageView actionFavor;
    @BindView(R.id.action_repost)
    ImageView actionRepost;
    private CommentAdapter mAdapter;
    private View headerView;

    @Override
    protected void loadViewLayout() {
        ButterKnife.bind(this);
    }

    @Override
    protected void bindViews() {

    }
    protected List<Comment> mDatas = new ArrayList<>();
    protected int mPageNow = 1;
    protected String news_url;
    protected String itemId;
    protected String news_title;
    protected String news_source;
    protected long news_time;
    protected int comments_count;
    protected boolean has_more;
    protected String news_content;
    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initCommonRecyclerView(mAdapter = new CommentAdapter(mDatas), null);
        headerView = View.inflate(this, R.layout.layout_webview, null);
        headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mAdapter.openLoadMore(10, true);
        View emptyView = View.inflate(this, R.layout.subscribe_list_empty_view, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mAdapter.setEmptyView(true, emptyView);
        Intent intent = getIntent();
        news_url = intent.getStringExtra(ConstanceValue.NEWS_URL);
        itemId = intent.getStringExtra(ConstanceValue.ITEM_ID);
        news_title = intent.getStringExtra(ConstanceValue.NEWS_TITLE);
        news_source = intent.getStringExtra(ConstanceValue.NEWS_SOURCE);
        news_time = intent.getExtras().getLong(ConstanceValue.NEWS_TIME);
        comments_count = intent.getExtras().getInt(ConstanceValue.COMMENTS_COUNT);
        news_content = intent.getStringExtra(ConstanceValue.NEWS_CONTENT);
        mAdapter.addHeaderView(createHeader());
        getData();

    }

    public abstract View createHeader();

    @Override
    protected BaseDetailPresenter createPresenter() {
        return new BaseDetailPresenter(this);
    }

    public static void startNews(Context context, String news_url, String itemId, String news_title, String news_source, long news_time, int comments_count, String news_content) {
        start(context, NewsDetailActivity.class, news_url, itemId, news_title, news_source, news_time, comments_count, news_content);
    }

    private static void start(Context context, Class activityClass, String news_url, String itemId, String news_title, String news_source, long news_time, int comments_count,String news_content) {
        Intent intent = new Intent(context, activityClass);
        intent.putExtra(ConstanceValue.NEWS_URL, news_url);
        intent.putExtra(ConstanceValue.ITEM_ID, itemId);
        intent.putExtra(ConstanceValue.NEWS_TITLE, news_title);
        intent.putExtra(ConstanceValue.NEWS_SOURCE, news_source);
        intent.putExtra(ConstanceValue.NEWS_TIME, news_time);
        intent.putExtra(ConstanceValue.COMMENTS_COUNT, comments_count);
        intent.putExtra(ConstanceValue.NEWS_CONTENT, news_content);
        context.startActivity(intent);
    }


    protected void getData() {
        getNewsDetail();
        getComment();
    }

    private void getComment() {
        mvpPresenter.getComment(itemId, mPageNow);
    }


    private void getNewsDetail() {
        mvpPresenter.getNewsDetail(news_title,news_source,news_time,itemId,news_url,news_content);
    }

    @Override
    protected void setListener() {
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (!TextUtils.isEmpty(itemId)) {
                    mPageNow++;
                    getComment();
                }
            }
        });
    }


    @Override
    public void onGetCommentSuccess(List<Comment> response) {

        if (comments_count > 0) {
            actionCommentCount.setVisibility(View.VISIBLE);
            actionCommentCount.setText(comments_count + "");
        }

        if(comments_count > 10){
            has_more = true;
        }else {
            has_more = false;
        }

        if (response.size() > 0) {
            mAdapter.notifyDataChangedAfterLoadMore(response, has_more);
        } else {
            mAdapter.notifyDataChangedAfterLoadMore(false);
        }
    }

    @OnClick({R.id.action_commont_layout, R.id.action_favor, R.id.action_repost})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.action_commont_layout:
                recyclerView.smoothScrollToPosition(1);
                break;
            case R.id.action_favor:
                break;
            case R.id.action_repost:
                break;
        }
    }
}
