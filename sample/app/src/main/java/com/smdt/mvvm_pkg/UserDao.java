package com.smdt.mvvm_pkg;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by aplite_pc302 on 1/28/19.
 */
@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("delete from User")
    void deleteAllUsers();

    @Query("select * from User order by id desc")
    LiveData<List<User>> getAllUsers();

}
