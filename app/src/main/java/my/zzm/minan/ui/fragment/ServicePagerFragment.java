package my.zzm.minan.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseFragment;
import my.zzm.minan.model.News;
import my.zzm.minan.ui.adapter.NewsAdapter;
import my.zzm.minan.ui.adapter.TitlePagerAdapter;
import my.zzm.minan.ui.view.colortrackview.ColorTrackTabLayout;

/**
 * Created by AlexanDaking on 17/7/10.
 */

public abstract class ServicePagerFragment extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.bt_service_ll)
    LinearLayout bt_service_ll;
    @BindView(R.id.service_top_btn)
    RelativeLayout service_top_btn;

    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.bt4)
    Button bt4;
    @BindView(R.id.bt5)
    Button bt5;
    @BindView(R.id.bt6)
    Button bt6;


    protected abstract String[] getTitles();

    protected abstract BaseFragment getFragment(int position);

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void processLogic() {
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < getTitles().length; i++) {
            Log.i("servicepage", getTitles().length+"");
            BaseFragment fragment = getFragment(i);
            fragments.add(fragment);
        }
        vp.setAdapter(new TitlePagerAdapter(getChildFragmentManager(), fragments, getTitles()));
        vp.setOffscreenPageLimit(getTitles().length);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    service_top_btn.setVisibility(View.VISIBLE);
                    bt_service_ll.setVisibility(View.GONE);
                }else if(position==1){
                    service_top_btn.setVisibility(View.GONE);
                    bt_service_ll.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab.setupWithViewPager(vp);
    }



    @Override
    protected void setListener() {
    }
}
