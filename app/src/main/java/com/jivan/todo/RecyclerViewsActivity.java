package com.jivan.todo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jivan.todo.helpers.TestAdapter;
import com.jivan.todo.models.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewsActivity extends AppCompatActivity {
    List<Test> testList = new ArrayList<>();
    RecyclerView rvTest;
    TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_views);

        rvTest = findViewById(R.id.rvTest);
        rvTest.setLayoutManager(new LinearLayoutManager(this));


        adapter = new TestAdapter(testList);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        testList.add(new Test("Title1", "Description1", R.drawable.baseline_add_24));
        testList.add(new Test("Title 2", "Description 2", R.drawable.ic_launcher_background));
        rvTest.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }
}