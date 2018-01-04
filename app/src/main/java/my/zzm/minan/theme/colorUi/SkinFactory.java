package my.zzm.minan.theme.colorUi;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

import my.zzm.minan.theme.colorUi.widget.ColorImageView;
import my.zzm.minan.theme.colorUi.widget.ColorLinearLayout;
import my.zzm.minan.theme.colorUi.widget.ColorRelativeLayout;
import my.zzm.minan.theme.colorUi.widget.ColorTextView;
import my.zzm.minan.theme.colorUi.widget.ColorView;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class SkinFactory implements LayoutInflaterFactory {

    private AppCompatActivity mActivity;

    public SkinFactory(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        View view = null;
        boolean isColorUi = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "isColorUi", false);
        if (!isColorUi) return delegateCreateView(parent, name, context, attrs);

        switch (name) {
            case "TextView":
                view = new ColorTextView(context, attrs);
                break;
            case "ImageView":
                view = new ColorImageView(context, attrs);
//                Logger.i("ImageView 转换成"+view.getClass().getSimpleName());
                break;
            case "RelativeLayout":
                view = new ColorRelativeLayout(context, attrs);
                break;
            case "LinearLayout":
                view = new ColorLinearLayout(context, attrs);
                break;
            case "View":
                view = new ColorView(context, attrs);
                break;
        }
        if (view == null) {
            view = delegateCreateView(parent, name, context, attrs);
        }
        return view;
    }

    private View delegateCreateView(View parent, String name, Context context, AttributeSet attrs) {
        AppCompatDelegate delegate = mActivity.getDelegate();
        return delegate.createView(parent, name, context, attrs);
    }
}

