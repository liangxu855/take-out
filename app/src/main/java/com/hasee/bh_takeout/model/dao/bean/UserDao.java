package com.hasee.bh_takeout.model.dao.bean;

import android.content.Context;
import android.widget.Toast;

import com.hasee.bh_takeout.MyApplication;
import com.hasee.bh_takeout.bean.User;
import com.hasee.bh_takeout.model.dao.DBHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24 0024.
 * 针对用户数据库 编写的dao
 */

public class UserDao {
    private Dao<UserBean, Integer> userDao;

    public UserDao(Context mContext) {
        DBHelper dbHelper = DBHelper.getInstance();
        try {
            userDao = dbHelper.getDao(UserBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param user
     * @return
     * @des 添加一个user对象
     */
    public int addUserBean(UserBean user) {
        int id = 0;
        try {
            id = userDao.create(user);
            Toast.makeText(MyApplication.getContext(),"添加成功"+id,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void addAll(List<UserBean> users) {
        for (UserBean user : users) {
            addUserBean(user);
        }
    }

    /**
     * @return 表的集合
     * @des 查询表中所有属性
     */
    public List<UserBean> findAll() {
        try {
            return userDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据对象删除某条数据
     */
    public void delete(UserBean user) {
        DeleteBuilder deleteBuilder = userDao.deleteBuilder();
        try {
            deleteBuilder.where().eq("_id",user._id);
            deleteBuilder.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有数据
     */
    public void removeAll(List<UserBean> users){
        for (UserBean user: users) {
            delete(user);
        }
    }

    /**
     * @des 修改数据库
     * @param user
     */
    public void update(UserBean user){
        try {
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
