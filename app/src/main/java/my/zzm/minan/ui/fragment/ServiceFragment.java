package my.zzm.minan.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseFragment;
import my.zzm.minan.utils.ConstanceValue;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class ServiceFragment extends ServicePagerFragment {
    private String[] titles = new String[]{"求助案例", "民安服务"};
    private String[] titlesCode = new String[]{"9", "10"};

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_service, null);
    }

    @Override
    protected String[] getTitles() {
        return titles;
    }

    @Override
    protected BaseFragment getFragment(int position) {
        return ServiceFragment.newInstance(titlesCode[position]);
    }

    public static ServiceListFragment newInstance(String code) {
        ServiceListFragment fragment = new ServiceListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
        super.bindViews(view);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
