package com.smdt.mvvm_pkg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aplite_pc302 on 1/29/19.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> userlist = new ArrayList<>();
    private ItemClickListener itemClickListener;

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);
        return new UserHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int position) {
        User currentUser = userlist.get(position);
        userHolder.db_id.setText(String.valueOf(currentUser.getId()));
        userHolder.name.setText(currentUser.getUsr_name());
        userHolder.email.setText(currentUser.getUsr_email());
    }

    public void updateusers(List<User> users) {
        this.userlist = users;
        notifyDataSetChanged();
    }

    public User getUserAtPosition(int position) {
        return userlist.get(position);
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(User user);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;
        private TextView db_id;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            db_id = itemView.findViewById(R.id.db_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (itemClickListener != null && position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClick(userlist.get(position));
                    }
                }
            });
        }
    }
}
