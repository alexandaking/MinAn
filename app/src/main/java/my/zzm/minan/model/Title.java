package my.zzm.minan.model;

import java.io.Serializable;

/**
 * Created by AlexanDaking on 17/7/3.
 */

public class Title implements Serializable {
    public String TitleStr;
    public String TitleCode;

    public Title(String titleStr, String titleCode) {
        TitleStr = titleStr;
        TitleCode = titleCode;
    }
}
