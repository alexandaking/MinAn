package my.zzm.minan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AlexanDaking on 17/7/1.
 */


/*{
        "result": 1,
        "message": "\u7d22\u53d6\u6210\u529f",
        "value": [
        {
        "news_id": 163,
        "news_category": "\u65b0\u95fb",
        "news_category_id": 2,
        "title": "\u6c11\u5b89\u624b\u673a\u5e94\u7528\u7528\u6237\u4eab\u53d7\u6298\u6263\u7684\u4e2d\u56fd\u9910\u9986",
        "news_intro_article": "",
        "publish_time": 1499011200,
        "status": 1,
        "comment_num": 11,
        "mark": "0",
        "is_large": false,
        "tags": [],
        "news_url": "http:\/\/servertrj.com\/news\/index\/163",
        "Preview_image1": "http:\/\/servertrj.com\/assets\/uploads\/news\/thumb\/1507f-jpg.jpeg",
        "Preview_image2": "",
        "Preview_image3": "",
        "news_colour": "1",
        "news_source": "",
        "news_article_genre": "gallery"
        },*/

public class News {
    public String news_id;
    @SerializedName("abstract")
    public String news_category;
    public int news_category_id;
    public String title;
    public String news_intro_article;
    public long publish_time;
    public int status;
    public int comments_count;
    public int mark;
    public boolean is_large;
    public String tag;
    public String news_url;
    public String news_article_genre;
    public Boolean is_three;
    public Boolean is_small;
    public String Preview_image1;
    public String Preview_image2;
    public String Preview_image3;
    public String html_content;
    public int news_colour;
    public String news_source;
}
