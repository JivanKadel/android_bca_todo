package com.jivan.todo.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jivan.todo.R;
import com.jivan.todo.models.Test;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    private List<Test> testList;

    public TestAdapter(List<Test> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Test test = testList.get(position);
        holder.tvTodoTitle.setText(test.getTitle());
        holder.tvTodoDesc.setText(test.getDescription());
        holder.imageView.setImageResource(test.getDrawable());
    }


    @Override
    public int getItemCount() {
        return testList.size();
    }

    static class TestViewHolder extends RecyclerView.ViewHolder {
        TextView tvTodoTitle, tvTodoDesc;
        ImageView imageView;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTodoTitle = itemView.findViewById(R.id.tvTestTitle);
            tvTodoDesc = itemView.findViewById(R.id.tvTestDesc);
            imageView = itemView.findViewById(R.id.ivDrawable);
        }
    }
}
