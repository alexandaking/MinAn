package my.zzm.minan.utils;

import android.widget.Toast;

import my.zzm.minan.base.BaseApplication;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class ToastUtils {

    private static Toast mToast;

    /**
     * 显示Toast
     */
    public static void showToast( CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }
}
