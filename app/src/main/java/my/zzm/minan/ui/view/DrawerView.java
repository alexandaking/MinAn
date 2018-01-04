package my.zzm.minan.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import de.hdodenhof.circleimageview.CircleImageView;
import my.zzm.minan.R;
import my.zzm.minan.base.BaseApplication;
import my.zzm.minan.ui.activity.LoginActivity;
import my.zzm.minan.ui.activity.MainActivity;
import my.zzm.minan.ui.activity.SettingsActivity;
import my.zzm.minan.utils.ImageLoaderUtils;

/**
 * Created by AlexanDaking on 17/7/5.
 */

public class DrawerView implements View.OnClickListener {
    private final Activity activity;
    SlidingMenu localSlidingMenu;

    //侧边栏顶部
    public LinearLayout layout_login;
    public LinearLayout layout_unlogin;
    private CircleImageView navigation_profile_image;
    private TextView tv_navigation_username;
    private TextView tv_navigation_user_intro;

    //侧边栏底部
    private LinearLayout ll_favorite;
    private LinearLayout ll_night_mode;
    private LinearLayout ll_cache_clean;
    private RelativeLayout layout_my_page_personal_info;
    private RelativeLayout layout_mine_item_notification;
    private RelativeLayout layout_my_page_report;
    private RelativeLayout layout_my_page_about_us;
    private RelativeLayout layout_comment_us;
    private RelativeLayout layout_send_to;
    private RelativeLayout layout_setting_current_account;
    private RelativeLayout layout_logout;

    //用户认证
    private ImageView userVerified;

    public DrawerView(Activity activity) {
        this.activity = activity;
    }

    public SlidingMenu initSlidingMenu() {
        localSlidingMenu = new SlidingMenu(activity);
        localSlidingMenu.setMode(SlidingMenu.LEFT);//设置左右滑菜单
        localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//设置要使菜单滑动，触碰屏幕的范围
        localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
        localSlidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
        localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
        localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu滑动时的渐变程度
        localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);//使SlidingMenu附加在Activity右边
        localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//设置menu的布局文件
        localSlidingMenu.refreshDrawableState();
        localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            public void onOpened() {
            }
        });
        localSlidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {

            @Override
            public void onClosed() {
                // TODO Auto-generated method stub

            }
        });
        initView();
        refreshAvatar();

        return localSlidingMenu;
    }

    private void initView() {

        layout_login = (LinearLayout) localSlidingMenu.findViewById(R.id.layout_login);
        layout_unlogin = (LinearLayout) localSlidingMenu.findViewById(R.id.layout_unlogin);
        layout_unlogin.setOnClickListener(this);
        navigation_profile_image = (CircleImageView) localSlidingMenu.findViewById(R.id.navigation_profile_image);
        tv_navigation_username = (TextView) localSlidingMenu.findViewById(R.id.tv_navigation_username);
        tv_navigation_user_intro = (TextView) localSlidingMenu.findViewById(R.id.tv_navigation_user_intro);

        ll_favorite = (LinearLayout) localSlidingMenu.findViewById(R.id.ll_favorite);
        ll_night_mode = (LinearLayout) localSlidingMenu.findViewById(R.id.ll_night_mode);
        ll_cache_clean = (LinearLayout) localSlidingMenu.findViewById(R.id.ll_cache_clean);
        layout_my_page_personal_info = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_my_page_personal_info);
        layout_mine_item_notification = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_mine_item_notification);
        layout_comment_us = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_comment_us);
        layout_comment_us.setOnClickListener(this);
        layout_send_to = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_send_to);
        layout_send_to.setOnClickListener(this);
        layout_setting_current_account = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_setting_current_account);
        layout_setting_current_account.setOnClickListener(this);
        layout_logout = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_logout);
        layout_logout.setOnClickListener(this);
        layout_my_page_report = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_my_page_report);
        layout_my_page_about_us = (RelativeLayout) localSlidingMenu.findViewById(R.id.layout_my_page_about_us);

        Log.d("DV","检查登录状态"+((BaseApplication) activity.getApplicationContext()).isLogin());
        if(((BaseApplication) activity.getApplicationContext()).isLogin()){
            layout_login.setVisibility(View.VISIBLE);
            layout_unlogin.setVisibility(View.GONE);
            layout_logout.setVisibility(View.VISIBLE);
        }else{
            layout_unlogin.setVisibility(View.VISIBLE);
            layout_login.setVisibility(View.GONE);
            layout_logout.setVisibility(View.GONE);
        }
    }

    public void refreshAvatar() {
        Log.d("ct", " IS LOGIN:" + ((BaseApplication) activity.getApplicationContext()).isLogin());
        if (((BaseApplication) activity.getApplicationContext()).isLogin()) {
            if(!"".equals(((BaseApplication)activity.getApplicationContext()).getAvatar())) {
                Picasso.with(activity).load(((BaseApplication) activity.getApplicationContext()).getAvatar()).placeholder(R.drawable.icon_avatar).fit().into(navigation_profile_image);
                Picasso.Builder builder = new Picasso.Builder(activity);
                builder.listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.d("error", "Picasso image loading error drawer:" + exception.getMessage());
                        exception.printStackTrace();
                    }

                });
            }else{
                Picasso.with(activity).load(R.drawable.icon_avatar).fit().into(navigation_profile_image);
            }

            tv_navigation_username.setText(((BaseApplication) activity.getApplicationContext()).getUser_name());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_unlogin:
                Log.d("mn", "AVATAR go LoginActivity");
                activity.startActivity(new Intent(activity, LoginActivity.class));
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.layout_login:
                Log.d("mn", "in login btn");
                break;

            case R.id.ll_favorite:
                Log.d("mn", "favorite");
                break;

            case R.id.ll_night_mode:
                break;

            case R.id.ll_cache_clean:
                break;


            case R.id.layout_my_page_personal_info:
                Log.d("mn", "AVATAR go Event");
                break;

            case R.id.layout_mine_item_notification:
                Log.d("mn", "AVATAR onclick imgAvatar");
                break;
            case R.id.layout_my_page_report:
                Log.d("mn", "AVATAR onclick imgAvatar");
                break;
            case R.id.layout_my_page_about_us:
                Log.d("mn", "AVATAR onclick imgAvatar");
                break;
            case R.id.layout_logout:
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (((BaseApplication) activity.getApplicationContext()).isLogin()) {
                    if (wechat.isAuthValid()) {
                        wechat.removeAccount(true);
                        Toast.makeText(activity, "成功退出您的微信账号", Toast.LENGTH_SHORT).show();
                    } else if (qq.isAuthValid()) {
                        qq.removeAccount(true);
                        Toast.makeText(activity, "成功退出您的QQ账号", Toast.LENGTH_SHORT).show();
                    } else if (sina.isAuthValid()) {
                        sina.removeAccount(true);
                        Toast.makeText(activity, "成功退出您的新浪微博账号", Toast.LENGTH_SHORT).show();
                    }
                    localSlidingMenu.toggle();
                }else{
                    Toast.makeText(activity, "您还未登陆", Toast.LENGTH_SHORT).show();
                }
                ((BaseApplication) activity.getApplicationContext()).clear();
                localSlidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
                    @Override
                    public void onClosed() {
                        initSlidingMenu();
                        refreshAvatar();
                    }
                });
                break;

            default:
                break;
        }
    }
}

