package my.zzm.minan.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseMvpFragment;
import my.zzm.minan.presenter.SOSPresenter;
import my.zzm.minan.presenter.ServicePresenter;
import my.zzm.minan.view.ISOSView;
import my.zzm.minan.view.IServiceView;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class SOSFragment extends BaseMvpFragment<SOSPresenter> implements ISOSView {
    @BindView(R.id.sos_call_btn)
    ImageView sos_call_btn;
    @Override
    protected SOSPresenter createPresenter() {
        return new SOSPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_sos, null);
    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.sos_call_btn)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sos_call_btn:
                Log.d("sos","SOS CALL");
                try {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:1800889958"));
                    startActivity(intent);
                    }else{
                    }
                } catch (Exception e) {
                    Log.e("Demo application", "Failed to invoke call", e);
                }
                break;
        }
    }
}
