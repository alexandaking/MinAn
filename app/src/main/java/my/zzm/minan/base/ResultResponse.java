package my.zzm.minan.base;

/**
 * Created by AlexanDaking on 17/7/1.
 */

public class ResultResponse<T> {
    public String result;
    public String message;
    public String success;
    public T data;

    public ResultResponse(String _message, T result) {
        message = _message;
        data = result;
    }
}
