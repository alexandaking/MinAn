package my.zzm.minan.view;

import java.util.List;

import my.zzm.minan.model.News;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public interface INewsListView {
    void onGetNewsListSuccess(List<News> response);
    void onError();
}
