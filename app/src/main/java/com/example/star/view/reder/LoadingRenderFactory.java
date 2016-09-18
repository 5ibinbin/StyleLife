package com.example.star.view.reder;

/**
 * Created by：Cral-Gates on 16/9/18 22:07
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/18
 * Description:
 */
import android.content.Context;
import android.util.SparseArray;

import java.lang.reflect.Constructor;

public final class LoadingRenderFactory {
    private static final SparseArray<Class<? extends LoadingRender>> LOADING_RENDERERS = new SparseArray<>();

    static {
        LOADING_RENDERERS.put(1, LevelLoadingRender.class);
    }

    private LoadingRenderFactory() {

    }

    public static LoadingRender createLoadingRenderer(Context context, int loadingRendererId) throws Exception {
        Class<?> loadingRendererClazz = LOADING_RENDERERS.get(loadingRendererId);
        Constructor<?>[] constructors = loadingRendererClazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes != null
                    && parameterTypes.length == 1
                    && parameterTypes[0].equals(Context.class)) {
                constructor.setAccessible(true);
                return (LoadingRender) constructor.newInstance(context);
            }
        }

        throw new InstantiationException();
    }
}