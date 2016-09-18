package com.example.star.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by：Cral-Gates on 16/8/20 21:02
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/20
 * Description:
 */
public abstract class BaseDao<T, Integer> {
    private Dao<T, Integer> mDao;

    private DataBaseHelper mDataBaseHelper;

    public BaseDao(Context context, Class<T> clazz) {
        try {
            mDataBaseHelper = new DataBaseHelper(context);
            mDao = mDataBaseHelper.getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<T, Integer> getDao() {
        return mDao;
    }

    /**
     * @param item 插入的消息体
     * @return 返回ID
     */
    public int insert(T item) {
        int id = -1;

        try {
            id = mDao.create(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @param id 根据ID进行查询
     * @return 返回消息体
     */
    public T getById(Integer id) {

        try {
            return mDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param item 根据消息体进行更新
     * @return 返回ID;
     */
    public int update(T item) {
        int id = -1;
        try {
            id = mDao.update(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @param primaryKey 主键
     * @return 返回被删除的ID
     */
    public int delete(Integer primaryKey) {
        int id = -1;
        try {
            id = mDao.deleteById(primaryKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @param field 查询依据
     * @return 根据查询依据的值比较选出最大值, 按照降序排列选择第一个
     * @throws SQLException
     */
    public T maxFieldItem(String field) throws SQLException {
        return mDao.queryBuilder().orderBy(field, false).limit(1L).query().get(0);
    }

    /**
     * @param field 查询依据
     * @return 根据查询依据的值比较选出最小值, 按照升序排列选择第一个
     */
    public T minFieldItem(String field) {
        try {
            return mDao.queryBuilder().orderBy(field, true).limit(1L).query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return 返回消息体列表
     */
    public List<T> getAll() {
        try {
            return mDao.queryBuilder().orderBy("id", false).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * @param field 依赖的值
     * @param isAsc 升序或者降序
     * @return 获取消息列表
     */
    public List<T> getAll(String field, boolean isAsc) {
        List<T> list = new ArrayList<>();
        try {
            list = mDao.queryBuilder().orderBy(field, isAsc).query();
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    /**
     * @param items 利用Transaction进行批量插入
     */
    public void insertWithTransaction(final List<T> items) {
        try {
            TransactionManager.callInTransaction(mDataBaseHelper.getConnectionSource(), new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (T t : items) {
                        mDao.create(t);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
