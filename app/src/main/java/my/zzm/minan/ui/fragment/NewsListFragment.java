package my.zzm.minan.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseMvpFragment;
import my.zzm.minan.model.News;
import my.zzm.minan.presenter.NewsListPresenter;
import my.zzm.minan.ui.activity.BaseNewsActivity;
import my.zzm.minan.ui.adapter.NewsAdapter;
import my.zzm.minan.ui.view.LoadingFlashView;
import my.zzm.minan.utils.ConstanceValue;
import my.zzm.minan.view.INewsListView;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class NewsListFragment extends BaseMvpFragment<NewsListPresenter> implements INewsListView {
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.loadingView)
    LoadingFlashView loadingView;
    private String mTitleCode = "";
    protected List<News> mDatas = new ArrayList<>();
    protected BaseQuickAdapter mAdapter;

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.layout_recyclerview, null);
        ButterKnife.bind(this, v);
        return v;
    }

    public static NewsListFragment newInstance(String code) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
    }

    @Override
    protected void processLogic() {
        initCommonRecyclerView(createAdapter(), null);
        mTitleCode = getArguments().getString(ConstanceValue.DATA);
//        srl.measure(0, 0);
//        srl.setRefreshing(true);
    }

    protected BaseQuickAdapter createAdapter() {
        Log.i("news list fragment", "加载数据");
        return mAdapter = new NewsAdapter(mDatas);
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        if (TextUtils.isEmpty(mTitleCode))
            mTitleCode = getArguments().getString(ConstanceValue.DATA);
        if (mvpPresenter.mvpView == null)
            mvpPresenter = createPresenter();
        getData();
    }

    private void getData() {
        if (mDatas.size() == 0) {

            Log.i("newslistfragment","数据为空");
            //没加载过数据
            if (loadingView == null) loadingView = get(R.id.loadingView);
            loadingView.setVisibility(View.VISIBLE);
            loadingView.showLoading();
        }else{

        }
        Log.i("newslistfragment","title code " + mTitleCode);
        mvpPresenter.getNewsList(mTitleCode);
    }

    @Override
    protected void setListener () {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        srl.setRefreshing(false);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view, int i) {
                News news = mDatas.get(i);
                String news_title = news.title;
                String news_source = news.news_source;
                long news_time = news.publish_time;
                String itemId = news.news_id;
                final String news_url = news.news_url;
                int comments_count = news.comments_count;
                Log.i("newslistfragment", news_title);
                String news_content = news.html_content;
                BaseNewsActivity.startNews(mContext, news_url, itemId, news_title, news_source, news_time,comments_count,news_content);

            }
        });
    }

    @Override
    public void onGetNewsListSuccess(List<News> response) {
        //由于最后一条重复 ，删除掉
        if (response.size() > 0) {
            //response.remove(response.size() - 1);
            loadingView.setVisibility(View.GONE);
            Log.e("111",response.get(0).title);
        }
        srl.setRefreshing(false);
        mDatas.addAll(0, response);
        mAdapter.notifyItemRangeChanged(0, response.size());
    }

    @Override
    public void onError() {
        srl.setRefreshing(false);
    }
}
