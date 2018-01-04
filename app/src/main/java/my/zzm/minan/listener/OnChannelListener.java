package my.zzm.minan.listener;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public interface OnChannelListener {
    void onItemMove(int starPos, int endPos);
    void onMoveToMyChannel(int starPos, int endPos);
    void onMoveToOtherChannel(int starPos, int endPos);
}