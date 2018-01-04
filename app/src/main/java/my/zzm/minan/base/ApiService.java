package my.zzm.minan.base;

import java.util.List;

import my.zzm.minan.model.Comment;
import my.zzm.minan.model.News;
import retrofit2.http.GET;
import retrofit2.http.Header;

import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public interface ApiService {

    String KEY = "123";
    //LIVE SERVER//
    String HOST = "http://servertrj.com/";
    //DEV SERVER
    //String HOST = "http://bravonet.my/ct3/";

    String API_SERVER_URL = HOST + "api/";

    /**
     * 获取新闻数据列表
     */
    @GET("news/index/")
    Observable<ResultResponse<List<News>>> getNews(@Header("api_key") String key, @Query("category_id") String category_id);
    //Call<News> operationGetNewsHighlight(@Header("api_key") String key, @Path("category_id") String category_id);

    @GET("news/get_comments/{newsid}/{count}/{offset}")
    Observable<ResultResponse<List<Comment>>> getComment(@Header("api_key") String key, @Path("newsid") String newsid, @Path("count")String count, @Path("offset")String offset);
}

