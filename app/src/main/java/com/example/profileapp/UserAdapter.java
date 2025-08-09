package com.example.profileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    List<UserModel> userList;
    Database db;
    ActivityResultLauncher<Intent> editLauncher;

    public UserAdapter(Context context, ArrayList<UserModel> userList, Database db, ActivityResultLauncher<Intent> editLauncher) {
        this.context = context;
        this.userList = userList;
        this.db = db;
        this.editLauncher = editLauncher;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel user = userList.get(position);

        holder.name.setText("Name: " + user.getName());
        holder.age.setText("Age: " + user.getAge());
        holder.index.setText("Index: " + user.getIndex());
        holder.programme.setText("Programme: " + user.getProgramme());
        holder.dob.setText("DOB: " + user.getDob());

        holder.deleteBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Confirmation")
                    .setMessage("Are you sure you want to delete this user?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        db.deleteUser(user.getIndex());
                        userList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "User deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        holder.editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, Edit.class);
            intent.putExtra("name", user.getName());
            intent.putExtra("age", user.getAge());
            intent.putExtra("index_number", user.getIndex());
            intent.putExtra("programme", user.getProgramme());
            intent.putExtra("dob", user.getDob());
            editLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, index, programme, dob;
        Button editBtn, deleteBtn;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameText);
            age = itemView.findViewById(R.id.ageText);
            index = itemView.findViewById(R.id.indexText);
            programme = itemView.findViewById(R.id.programmeText);
            dob = itemView.findViewById(R.id.dobText);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
