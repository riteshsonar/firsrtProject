package com.smdt.mvvm_pkg;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by aplite_pc302 on 1/29/19.
 */

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAlluser();
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }
    public void updateUser(User user){
        userRepository.updateUser(user);
    }
    public void deleteUser(User user){
        userRepository.deleteUser(user);
    }
    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
}
