package my.zzm.minan.model;

/**
 * Created by AlexanDaking on 17/7/3.
 */

public class Call {
    public String title;
    public String desc;
    public String imgurl;
    public String href;
    public String mask;

    @Override
    public String toString() {
        return "Call [title=" + title + ", desc=" + desc +", imgurl=" + imgurl + ", href="
                + href + ", mask=" + mask + "]";
    }
}
