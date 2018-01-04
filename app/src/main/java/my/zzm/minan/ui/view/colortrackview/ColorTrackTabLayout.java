package my.zzm.minan.ui.view.colortrackview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import my.zzm.minan.theme.colorUi.ColorUiInterface;
import my.zzm.minan.theme.colorUi.util.ViewAttributeUtil;
import my.zzm.minan.ui.view.SwipeBackLayout;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;
import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class ColorTrackTabLayout extends TabLayout implements ColorUiInterface {
    private int mAttr_background = -1;
    private int mAttr_selectedTextColor = -1;
    private int mTabTextSize;
    private int mTabSelectedTextColor;
    private int mTabTextColor;
    private ColorTrackTabLayoutOnPageChangeListener mPageChangeListenter;
    private ColorTrackView preColorTrackView;
    private int mAttr_textColor = -1;

    private static final int INVALID_TAB_POS = -1;
    /*
    * 最后选中的postition
    */
    private int mLastSelectedTabPosition = INVALID_TAB_POS;
    public ColorTrackTabLayout(Context context) {
        this(context, null);
    }
    public ColorTrackTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, android.support.design.R.styleable.TabLayout,
                    defStyleAttr, android.support.design.R.style.Widget_Design_TabLayout);
            //自定义属性的background
            mAttr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
            mAttr_selectedTextColor = ViewAttributeUtil.getAttributeValue(attrs, "http://schemas.android.com/apk/res-auto", "tabSelectedTextColor");
            mAttr_textColor = ViewAttributeUtil.getAttributeValue(attrs, "http://schemas.android.com/apk/res-auto", "tabTextColor");

            try {
                int tabTextAppearance = a.getResourceId(android.support.design.R.styleable.TabLayout_tabTextAppearance,
                        android.support.design.R.style.TextAppearance_Design_Tab);

                // Text colors/sizes come from the text appearance first
                final TypedArray ta = context.obtainStyledAttributes(tabTextAppearance,
                        android.support.v7.appcompat.R.styleable.TextAppearance);
                try {
                    //Tab字体大小
                    mTabTextSize = ta.getDimensionPixelSize(
                            android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize, 0);
                    //Tab文字颜色
                    mTabTextColor = ta.getColor(
                            android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor, 0);
                } finally {
                    ta.recycle();
                }

                //Tab文字选中颜色
                mTabSelectedTextColor = a.getColor(android.support.design.R.styleable.TabLayout_tabSelectedTextColor, Color.BLACK);
                //Tab默认颜色
                mTabTextColor = a.getColor(android.support.design.R.styleable.TabLayout_tabTextColor, mTabTextColor);

            } finally {
                a.recycle();
            }
        }
    }

    @Override
    public void addTab(@NonNull Tab tab, int position, boolean setSelected) {
        ColorTrackView colorTrackView = new ColorTrackView(getContext());
        colorTrackView.setProgress(setSelected ? 1 : 0);
        colorTrackView.setText(tab.getText() + "");
        colorTrackView.setTextSize(mTabTextSize);
        colorTrackView.setTag(position);
        colorTrackView.setTextChangeColor(mTabSelectedTextColor);
        colorTrackView.setTextOriginColor(mTabTextColor);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        colorTrackView.setLayoutParams(layoutParams);
        tab.setCustomView(colorTrackView);

        super.addTab(tab, position, setSelected);


        int selectedTabPosition = getSelectedTabPosition();
        if ((selectedTabPosition == INVALID_TAB_POS && position == 0) || (selectedTabPosition == position)) {
            setSelectedView(position);
        }
        setTabWidth(position, colorTrackView);
    }

    private void setTabWidth(int position, ColorTrackView colorTrackView) {
        ViewGroup tabView = getTabView(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        //手动测量一下
        colorTrackView.measure(w, h);
        params.width = colorTrackView.getMeasuredWidth() + tabView.getPaddingLeft() + tabView.getPaddingRight();
        //设置tabView的宽度
        tabView.setLayoutParams(params);
    }

    public ViewGroup getTabView(int position) {
        ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);
        return (ViewGroup) slidingTabStrip.getChildAt(position);
    }

    /**
     * 设置每个Tab的左内边距和右内边距
     *
     * @param left
     * @param right
     */
    public void setTabPaddingLeftAndRight(int left, int right) {
        try {
            Field mTabPaddingStartField = TabLayout.class.getDeclaredField("mTabPaddingStart");
            Field mTabPaddingEndField = TabLayout.class.getDeclaredField("mTabPaddingEnd");

            mTabPaddingStartField.setAccessible(true);
            mTabPaddingEndField.setAccessible(true);

            mTabPaddingStartField.set(this, left);
            mTabPaddingEndField.set(this, right);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ViewPager mViewPager;

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean autoRefresh) {
        super.setupWithViewPager(viewPager, autoRefresh);
        try {
            if (viewPager != null)
                mViewPager = viewPager;
            //通过反射找到mPageChangeListener
            Field field = TabLayout.class.getDeclaredField("mPageChangeListener");
            field.setAccessible(true);
            TabLayoutOnPageChangeListener listener = (TabLayoutOnPageChangeListener) field.get(this);
            if (listener != null) {
                //删除自带监听
                viewPager.removeOnPageChangeListener(listener);
                mPageChangeListenter = new ColorTrackTabLayoutOnPageChangeListener(this);
                viewPager.addOnPageChangeListener(mPageChangeListenter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tabScrolled(int position, float positionOffset) {

        if (positionOffset == 0.0F) {
            return;
        }
        ColorTrackView currentTrackView = getColorTrackView(position);
        ColorTrackView nextTrackView = getColorTrackView(position + 1);
        currentTrackView.setDirection(1);
        currentTrackView.setProgress(1.0F - positionOffset);
        nextTrackView.setDirection(0);
        nextTrackView.setProgress(positionOffset);

        invalidate();
    }

    public ColorTrackView getColorTrackView(int position) {
        return (ColorTrackView) getTabAt(position).getCustomView();
    }

    public static class ColorTrackTabLayoutOnPageChangeListener extends TabLayoutOnPageChangeListener {

        private final WeakReference<ColorTrackTabLayout> mTabLayoutRef;
        private int mPreviousScrollState;
        private int mScrollState;

        public ColorTrackTabLayoutOnPageChangeListener(TabLayout tabLayout) {
            super(tabLayout);
            mTabLayoutRef = new WeakReference<>((ColorTrackTabLayout) tabLayout);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
            mPreviousScrollState = mScrollState;
            mScrollState = state;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            final boolean updateText = mScrollState != SCROLL_STATE_SETTLING ||
                    mPreviousScrollState == SCROLL_STATE_DRAGGING;

            if (updateText) {
                mTabLayoutRef.get().tabScrolled(position, positionOffset);
            }
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            ColorTrackTabLayout tabLayout = mTabLayoutRef.get();
            tabLayout.setSelectedView(position);
        }

    }


    protected void setSelectedView(int postion) {
        if (preColorTrackView != null)
            preColorTrackView.setProgress(0);
        ColorTrackView view = getColorTrackView(postion);
        view.setProgress(1);
        preColorTrackView = view;
    }

    //--------------------------动态换主题----------------------
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        for (int i = 0; i < getTabCount(); i++) {
            ColorTrackView colorTrackView = getColorTrackView(i);
            if (mAttr_textColor != -1) {
                TypedArray ta = themeId.obtainStyledAttributes(new int[]{mAttr_textColor});
                int resourceId = ta.getColor(0, 0);
                ta.recycle();
                if (mTabTextColor != resourceId)
                    mTabTextColor = resourceId;
                colorTrackView.setTextOriginColor(resourceId);
            }
            if (mAttr_selectedTextColor != -1) {
                TypedArray ta = themeId.obtainStyledAttributes(new int[]{mAttr_selectedTextColor});
                int resourceId = ta.getColor(0, 0);
                ta.recycle();
                colorTrackView.setTextChangeColor(resourceId);
            }


        }
        if (mAttr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, mAttr_background);
        }
//        ViewAttributeUtil.applyTextColor(this, themeId, mTabTextColor);
    }

    @Override
    public void removeAllTabs() {
        // Retain last selected position before removing all tabs
        mLastSelectedTabPosition = getSelectedTabPosition();
        super.removeAllTabs();
    }

    @Override
    public int getSelectedTabPosition() {
        // Override selected tab position to return your last selected tab position
        final int selectedTabPositionAtParent = super.getSelectedTabPosition();
        return selectedTabPositionAtParent == INVALID_TAB_POS ?
                mLastSelectedTabPosition : selectedTabPositionAtParent;
    }

    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
    }
}
