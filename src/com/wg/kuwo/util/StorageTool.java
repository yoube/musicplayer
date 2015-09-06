package com.wg.kuwo.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by EXP on 2015/7/26.
 */
public class StorageTool {
    public static String[] getStorageList(Context context){

        if(context==null)return null;
        //获取storage服务
        StorageManager storageManager = (StorageManager) context.getSystemService(Activity.STORAGE_SERVICE);
        Method method = null;
        try {
            method = storageManager.getClass().getMethod("getVolumePaths");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //判断是否是android 4.0以上
        if(Build.VERSION.SDK_INT>=14){
            try {
                return (String[]) method.invoke(storageManager);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }else if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){//否则判断外存是否存在
            return new String[]{Environment.getExternalStorageDirectory().getAbsolutePath()};
        }

        return null;
    }

}
