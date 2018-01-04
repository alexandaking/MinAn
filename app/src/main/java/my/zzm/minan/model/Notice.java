package my.zzm.minan.model;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class Notice {
    public int type;
    public Object content;

    public Notice(int type) {
        this.type = type;
    }

    public Notice(int type, Object content) {
        this.type = type;
        this.content = content;
    }

    public Notice() {
    }
}
