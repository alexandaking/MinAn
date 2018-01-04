package my.zzm.minan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseActivity;
import my.zzm.minan.base.BaseApplication;

/**
 * Created by AlexanDaking on 17/7/24.
 */

public class SettingsActivity extends BaseActivity{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_logout)
    RelativeLayout layout_logout;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.settings);
        ButterKnife.bind(this);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void bindViews() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        title.setText("设置");
    }

    @OnClick({R.id.back_btn,R.id.layout_send_to,R.id.layout_comment_us,R.id.layout_about_us,R.id.layout_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.layout_send_to:
                Toast.makeText(SettingsActivity.this, "点击了推荐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_comment_us:
                Toast.makeText(SettingsActivity.this, "点击了评价", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_about_us:
                Toast.makeText(SettingsActivity.this, "点击了关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_logout:
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (((BaseApplication) getApplicationContext()).isLogin()) {
                    if (wechat.isAuthValid()) {
                        wechat.removeAccount(true);
                        Toast.makeText(SettingsActivity.this, "成功退出您的微信账号", Toast.LENGTH_SHORT).show();
                    } else if (qq.isAuthValid()) {
                        qq.removeAccount(true);
                        Toast.makeText(SettingsActivity.this, "成功退出您的QQ账号", Toast.LENGTH_SHORT).show();
                    } else if (sina.isAuthValid()) {
                        sina.removeAccount(true);
                        Toast.makeText(SettingsActivity.this, "成功退出您的新浪微博账号", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SettingsActivity.this, "还没有登录", Toast.LENGTH_SHORT).show();
                }
                ((BaseApplication) getApplicationContext()).clear();
                break;
        }
    }

}
