package my.zzm.minan.ui.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import my.zzm.minan.R;
import my.zzm.minan.model.News;
import my.zzm.minan.theme.colorUi.util.ColorUiUtil;
import my.zzm.minan.utils.ConstanceValue;
import my.zzm.minan.utils.DateUtils;
import my.zzm.minan.utils.ImageLoaderUtils;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class NewsAdapter extends BaseQuickAdapter<News> {

    public NewsAdapter(List<News> data) {
        super(R.layout.item_news, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, News news) {
        //防止复用View没有改变主题，重新设置
        ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
        setGone(baseViewHolder);
        Log.i("news_adapter_load", news.news_article_genre);
        if (ConstanceValue.ARTICLE_GENRE_ARTICLE.equals(news.news_article_genre)) {
                if (news.is_small) {
                    //单图片文章
                    Log.i("newsadapter", "一张小图 = "+news.Preview_image1);
                    ImageLoaderUtils.displayImage(news.Preview_image1, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
                    baseViewHolder.setVisible(R.id.rlRightImg, true)
                            .setVisible(R.id.viewFill, true);
                } else {
                    //3张图片
                    baseViewHolder.setVisible(R.id.llCenterImg, true);
                    try {
                        Log.i("newsadapter", "三张图 = "+news.Preview_image1);
                        ImageLoaderUtils.displayImage(news.Preview_image1, (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));
                        ImageLoaderUtils.displayImage(news.Preview_image2, (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));
                        ImageLoaderUtils.displayImage(news.Preview_image3, (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }else if (ConstanceValue.ARTICLE_GENRE_GALLERY.equals(news.news_article_genre)) {
            //画廊类型
            Log.i("newsadapter", "一张大图 = "+news.Preview_image1);
            ImageLoaderUtils.displayImage(news.Preview_image1, (ImageView) baseViewHolder.getView(R.id.ivBigImg));
            baseViewHolder.setVisible(R.id.rlBigImg, true);
            //.setText(R.id.tvImgCount, news.image_list.size() + "图");
            }
            baseViewHolder.setText(R.id.tvTitle, news.title)
                    .setText(R.id.tvAuthorName, news.news_source)
                    .setText(R.id.tvCommentCount, news.comments_count + "评论")
                    .setText(R.id.tvTime, DateUtils.getShortTime(news.publish_time * 1000));
        if(news.news_category_id == 9){
            baseViewHolder.setVisible(R.id.tvCommentCount, false);
        }else if(news.news_category_id == 10){
            baseViewHolder.setVisible(R.id.tvCommentCount, false);
        }

    }

    private void setGone(BaseViewHolder baseViewHolder) {
        baseViewHolder.setVisible(R.id.viewFill, false)
                .setVisible(R.id.llCenterImg, false)
                .setVisible(R.id.rlBigImg, false)
                .setVisible(R.id.llVideo, false)
                .setVisible(R.id.rlRightImg, false);
    }

}
