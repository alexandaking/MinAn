package my.zzm.minan.ui.adapter;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import my.zzm.minan.R;
import my.zzm.minan.model.News;
import my.zzm.minan.theme.colorUi.util.ColorUiUtil;

/**
 * Created by AlexanDaking on 17/7/13.
 */

public class ServiceAdapter extends BaseQuickAdapter<News> {

    public ServiceAdapter(List<News> data) {
        super(R.layout.fragment_service, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, News news) {
        ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
    }
}
