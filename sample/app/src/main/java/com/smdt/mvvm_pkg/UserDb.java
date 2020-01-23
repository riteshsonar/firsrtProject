package com.smdt.mvvm_pkg;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by aplite_pc302 on 1/29/19.
 */
@Database(entities = {User.class}, version = 1)
public abstract class UserDb extends RoomDatabase {
    private static UserDb instance;
    public abstract UserDao userDao();




    public static synchronized UserDb getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),UserDb.class,"user_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
