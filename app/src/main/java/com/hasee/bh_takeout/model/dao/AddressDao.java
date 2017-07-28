package com.hasee.bh_takeout.model.dao;

import android.content.Context;

import com.hasee.bh_takeout.model.dao.bean.AddressBean;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24 0024.
 * 针对地址数据库 编写的dao
 */

public class AddressDao{
    private Dao<AddressBean, Integer> addressDao;

    public AddressDao(Context mContext) {
        DBHelper dbHelper = DBHelper.getInstance();
        try {
            addressDao = dbHelper.getDao(AddressBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param address
     * @return
     * @des 添加一个address对象
     */
    public int addAddress(AddressBean address) {
        int id = 0;
        try {

            id = addressDao.create(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void addAll(List<AddressBean> addresses) {
        for (AddressBean address : addresses) {
            addAddress(address);
        }
    }

    /**
     * @return 表的集合
     * @des 查询表中所有属性
     */
    public List<AddressBean> findAll() {
        try {
            return addressDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据对象删除某条数据
     */
    public void delete(int id) {
        DeleteBuilder deleteBuilder = addressDao.deleteBuilder();
        try {
            deleteBuilder.where().eq("_id",id);
            deleteBuilder.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有数据
     */
    public void removeAll(List<AddressBean> addresses){
        for (AddressBean address : addresses) {
            delete(address._id);
        }
    }

    /**
     * @des 修改数据库
     * @param address
     */
    public void update(AddressBean address){
        try {
            addressDao.update(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
