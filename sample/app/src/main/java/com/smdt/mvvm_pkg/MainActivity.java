package com.smdt.mvvm_pkg;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private UserViewModel userViewModel;
    private EditText name;
    private EditText email;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

//      view settings
        RecyclerView recyclerView = findViewById(R.id.userlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final UserAdapter userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                userAdapter.updateusers(users);
            }
        });

//      for swipe left/right to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                userViewModel.deleteUser(userAdapter.getUserAtPosition(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

//        get selected item.
        userAdapter.setOnItemClickListener(new UserAdapter.ItemClickListener() {
            @Override
            public void onItemClick(User user) {
                name.setText(user.getUsr_name());
                email.setText(user.getUsr_email());
                id=user.getId();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                String u_name = name.getText().toString();
                String u_email = email.getText().toString();
                User user = new User(u_name, u_email);
                userViewModel.insertUser(user);
                break;
            case R.id.update:
                if(id!=0) {
                    User updat_user = new User(name.getText().toString(),email.getText().toString());
                    updat_user.setId(id);
                    userViewModel.updateUser(updat_user);
                    id=0;
                }else{
                    Toast.makeText(this, "select existing user", Toast.LENGTH_SHORT).show();
                }
                break;

        }
//      after any changes reset views.
        name.setText("");
        email.setText("");
    }
}


