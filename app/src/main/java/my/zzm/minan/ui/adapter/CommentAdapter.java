package my.zzm.minan.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import my.zzm.minan.R;
import my.zzm.minan.model.Comment;
import my.zzm.minan.utils.ImageLoaderUtils;

/**
 * Created by AlexanDaking on 17/7/6.
 */

public class CommentAdapter extends BaseQuickAdapter<Comment> {
    public CommentAdapter(List<Comment> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Comment comment) {
        ImageLoaderUtils.displayAvatar(comment.cm_avatar, (ImageView) baseViewHolder.getView(R.id.ivAvatar));
        baseViewHolder
                .setText(R.id.ss_user, comment.cm_username)
                .setText(R.id.tvLikeCount, comment.cm_like)
                .setText(R.id.content, comment.cm_content)
                .setText(R.id.cm_datetime, comment.cm_datetime);
    }
}
