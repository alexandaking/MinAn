package my.zzm.minan.base;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
