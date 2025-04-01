package com.jivan.todo.models;

import android.graphics.drawable.Drawable;

public class Test {
    private String title;
    private String description;


    private int drawable;

    public Test(String test, String description, int drawable) {
        this.title = test;
        this.description = description;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDrawable() {
        return drawable;
    }
//    }
}
