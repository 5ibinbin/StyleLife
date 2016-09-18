package com.example.star;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.example.star.utils.log.LogUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by：Cral-Gates on 16/8/20 14:43
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/20
 * Description:
 */
public class StarApplication extends Application {
    public static String cacheDir = "";

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this.getApplicationContext());
        AVOSCloud.initialize(this,"XSR6DduuWLOhOGAxueY8ILUI-gzGzoHsz","6Q3zKE0Lba4yM806ybQaWxNg");
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        try {
            if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
                cacheDir = getApplicationContext().getExternalCacheDir().toString();
                LogUtils.d("sd卡", cacheDir);
            } else {
                cacheDir = getApplicationContext().getCacheDir().toString();
            }
        } catch (Exception e) {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    private boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
