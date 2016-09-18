package com.example.star.model.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by：Cral-Gates on 16/8/20 21:10
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/20
 * Description:
 */
@DatabaseTable(tableName = "tb_user")
public class BeanTest {
    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "age")
    private int age;

    public BeanTest(int age, int id, String name) {
        this.age = age;
        Id = id;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
