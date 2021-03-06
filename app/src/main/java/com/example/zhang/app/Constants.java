package com.example.zhang.app;

import java.io.File;

/**
 * @author zzh
 */
public class Constants {
    public static final boolean IS_LOGIN = true;
    private static final String PATH_DATA =
            AppApplication.getInstance().getExternalCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String CACHE_DIR_PATH = PATH_DATA + "/WebView";
    /**
     * http://www.wanandroid.com/
     */
    public static final String HOST = "http://baobab.kaiyanapp.com/api/";
    /**********************************Intent action start*******************************************/
    /**
     * Intent-action-创建notification
     */
    public static final String INTENT_ACTION_NOTIFICATION_CREATE = "intent_action_notification_create";
    /**
     * Intent-action-更新notification
     */
    public static final String INTENT_ACTION_NOTIFICATION_UPDATE = "intent_action_notification_update";
    /**********************************Intent action end*******************************************/
}
