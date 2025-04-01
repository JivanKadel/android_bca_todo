package com.jivan.todo;

public class Todo {
    private String title;
    private String description;
    private boolean isCompleted;

    public Todo(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean completed){
        isCompleted = completed;
    }
}
