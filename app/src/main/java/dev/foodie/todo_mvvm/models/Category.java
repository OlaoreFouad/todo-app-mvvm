package dev.foodie.todo_mvvm.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories_table")
public class Category {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int imageName;

    private String title;

    private int tasks;

    public Category() {
    }

    public Category(int imageName, String title, int tasks) {
        this.imageName = imageName;
        this.title = title;
        this.tasks = tasks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTasks() {
        return tasks;
    }

    public void setTasks(int tasks) {
        this.tasks = tasks;
    }
}
