package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Context context;
    private List<Task> taskList;
    private TaskDBHelper dbHelper;

    public TaskAdapter(Context context, List<Task> taskList, TaskDBHelper dbHelper) {
        this.context = context;
        this.taskList = taskList;
        this.dbHelper = dbHelper;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Button deleteBtn;

        public TaskViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.taskTitle);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.title.setText(task.getTitle());

        holder.deleteBtn.setOnClickListener(v -> {
            dbHelper.deleteTask(task.getId());
            taskList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskList.size());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}


