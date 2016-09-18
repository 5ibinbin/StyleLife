package com.example.star.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.star.model.entity.BeanTest;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by：Cral-Gates on 16/8/20 21:04
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/20
 * Description:根据bean来创建一个数据表格
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLENAME = "sqlite.db";

    private static DataBaseHelper instance;

    public DataBaseHelper(Context context) {
        super(context, TABLENAME, null, 1);
    }

    public static synchronized DataBaseHelper getInstance(Context context){

        if (instance == null){
            synchronized (DataBaseHelper.class){
                if (instance == null){
                    instance = new DataBaseHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, BeanTest.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, BeanTest.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
