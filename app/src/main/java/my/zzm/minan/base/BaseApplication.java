package my.zzm.minan.base;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mob.MobApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import my.zzm.minan.BuildConfig;
import my.zzm.minan.theme.colorUi.util.SharedPreferencesMgr;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class BaseApplication extends MobApplication {
    //private UserInfo userInfo;
    private static BaseApplication instance;

    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;
    private String token_id;
    private String user_id;
    private String user_name;
    private String avatar;
    private boolean isLogin;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferencesMgr.init(this, "wj");
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                instance);
        config.memoryCacheExtraOptions(480, 800);
        config.diskCacheExtraOptions(480, 800, null);
        config.diskCacheSize(100 * 1024 * 1024); // 100 MiB
        if (BuildConfig.DEBUG) {
            config.writeDebugLogs(); // Remove for release app
        }
        ImageLoader.getInstance().init(config.build());
    }


    /**用户信息*/
    public boolean isLogin() {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        isLogin = prefs.getBoolean("isLogin",false);
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putBoolean("isLogin", isLogin);
        editor.commit();
    }

    public String getToken_id() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        token_id = prefs.getString("token_id",token_id);
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putString("token_id", token_id);
        editor.commit();
    }

    public String getUser_id() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        user_id = prefs.getString("user_id", user_id);
        return user_id;
    }

    public void setUser_id(String user_id) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putString("user_id", user_id);
        editor.commit();
    }

    public String getUser_name() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = prefs.getString("user_name",user_name);
        return user_name;
    }

    public void setUser_name(String user_name) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putString("user_name", user_name);
        editor.commit();
    }

    public String getAvatar() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        avatar = prefs.getString("avatar",avatar);
        return avatar;
    }

    public void setAvatar(String avatar) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        editor.putString("avatar", avatar);
        editor.commit();
    }

    public void clear(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.user_name = "";
        this.token_id = "";
        this.user_id = "";
        this.avatar = "";
        this.isLogin = false;

        editor = prefs.edit();
        editor.putString("user_name",user_name);
        editor.putString("token_id",token_id);
        editor.putString("user_id",user_id);
        editor.putString("avatar",avatar);
        editor.putBoolean("isLogin", isLogin);

        editor.commit();

    }

    //    public UserInfo getUserInfo() {
//        return userInfo;
//    }
//
//    public void setUserInfo(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
    public static BaseApplication getInstance() {
        return instance;
    }

    public static BaseApplication getApp() {
        return instance;
    }

}

