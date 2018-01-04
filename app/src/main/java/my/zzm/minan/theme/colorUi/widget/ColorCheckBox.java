package my.zzm.minan.theme.colorUi.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

import my.zzm.minan.theme.colorUi.ColorUiInterface;
import my.zzm.minan.theme.colorUi.util.ViewAttributeUtil;


/**
 * Created by chengli on 15/6/8.
 */
public class ColorCheckBox extends CheckBox implements ColorUiInterface {

    private int attr_background = -1;

    public ColorCheckBox(Context context) {
        super(context);
    }

    public ColorCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ColorCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if(attr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
    }
}
