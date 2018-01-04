package my.zzm.minan.base;

import android.text.TextUtils;

import my.zzm.minan.utils.ToastUtils;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public abstract class SubscriberCallBack <T> extends BaseCallBack<ResultResponse<T>> {
    @Override
    public void onNext(ResultResponse response) {
        boolean isSuccess = (!TextUtils.isEmpty(response.message) && response.message.equals("success"))
                || !TextUtils.isEmpty(response.success) && response.success.equals("true");
        if (isSuccess) {
            onSuccess((T) response.data);
        } else {
            ToastUtils.showToast(response.message);
            onFailure(response);
        }
    }

    protected abstract void onSuccess(T response);
}

