package my.zzm.minan.view;

import java.util.List;
import java.util.StringTokenizer;

import my.zzm.minan.model.Comment;

/**
 * Created by AlexanDaking on 17/7/5.
 */

public interface IBaseDetailView {
    void onGetCommentSuccess(List<Comment> response);

    void onGetNewsDetailSuccess(String news_title, String news_source, long news_time, String itemId, String news_url, String news_content);
}
