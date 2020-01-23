package com.smdt.mvvm_pkg;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aplite_pc302 on 1/29/19.
 */

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> alluser;

    public UserRepository(Context context) {
        UserDb userDb=UserDb.getInstance(context);
        userDao=userDb.userDao();
        alluser=userDao.getAllUsers();
    }

    public void insertUser(User user){
        userDao.insertUser(user);
    }
    public void updateUser(User user){
        userDao.updateUser(user);
    }
    public void deleteUser(User user){
        userDao.deleteUser(user);
    }
    public LiveData<List<User>> getAlluser(){
        return alluser;
    }

    private void insertUserInDB(User user){

    }
}
