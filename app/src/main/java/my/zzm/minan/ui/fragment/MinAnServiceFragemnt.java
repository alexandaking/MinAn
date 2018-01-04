package my.zzm.minan.ui.fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseMvpFragment;
import my.zzm.minan.presenter.ServicePresenter;
import my.zzm.minan.view.IServiceView;

/**
 * Created by AlexanDaking on 17/7/11.
 */

public class MinAnServiceFragemnt extends BaseMvpFragment<ServicePresenter> implements IServiceView {
    @Override
    protected ServicePresenter createPresenter() {
        return new ServicePresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_recyclerview, null);
    }

    @Override
    protected void bindViews(View view) {

    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {

    }
}