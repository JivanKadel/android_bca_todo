package com.jivan.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class TodoDisplayActivity extends AppCompatActivity implements InputDialog.InputDialogListener {

    FloatingActionButton fbAdd;
    TodoAdapter adapter;

    RecyclerView recyclerView;
    List<Todo> todoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todo_display);


        fbAdd = findViewById(R.id.fbAdd);

        recyclerView = findViewById(R.id.todoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TodoAdapter(this, todoList);
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                deleteConfirmationDialog(position);
            }
        }).attachToRecyclerView(recyclerView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.todoDisplayActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        fbAdd.setOnClickListener(v -> {
            InputDialog inputDialog = new InputDialog();
            inputDialog.show(getSupportFragmentManager(), "TodoInputDialog");
        });
    }

    @Override
    public void onInputSubmitted(String input) {
        if (input != null && !input.trim().isEmpty()) {
            todoList.add(new Todo(input, "This is a description", false));
            adapter.notifyDataSetChanged();
            Snackbar snackbar = Snackbar.make(findViewById(R.id.todoDisplayActivity), "New Todo Added!", Snackbar.LENGTH_LONG);
            snackbar.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }

    public void deleteConfirmationDialog(int index) {
        AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                todoList.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        deleteDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteDialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });
        TextView title = new TextView(this);
// You Can Customise your Title here
        title.setText("DELETE TODO?");
        title.setBackgroundColor(Color.RED);
        title.setPadding(10, 24, 10, 24);

        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        deleteDialog.setCustomTitle(title);
        deleteDialog.show();
    }
}