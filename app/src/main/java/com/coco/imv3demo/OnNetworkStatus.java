package com.coco.imv3demo;


/**
 * Created by ydx on 16-12-30.
 */

public interface OnNetworkStatus<T> {
    void onLoading();
    void onLoaded();
    void onSuccess(T t);
    void onFail(String string);

}
