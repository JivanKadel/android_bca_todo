package com.jivan.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<Todo> todoList;
    private Context context;

    public TodoAdapter(Context context, List<Todo> todoList) {
        this.todoList = todoList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.titleTextView.setText(todo.getTitle());
        holder.descriptionTextView.setText(todo.getDescription());
        holder.completedCheckBox.setChecked(todo.getIsCompleted());

        holder.completedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.setIsCompleted(isChecked);
        });

    }


    @Override
    public int getItemCount() {
        return todoList.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;
        CheckBox completedCheckBox;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.todoTitle);
            descriptionTextView = itemView.findViewById(R.id.todoDescription);
            completedCheckBox = itemView.findViewById(R.id.todoCheckbox);
        }
    }
}
