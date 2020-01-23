package com.smdt.mvvm_pkg;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by aplite_pc302 on 1/28/19.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String usr_name;
    private String usr_email;

    public User(String usr_name, String usr_email) {
        this.usr_name = usr_name;
        this.usr_email = usr_email;
    }

    public String getUsr_name() {
        return usr_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }
}
