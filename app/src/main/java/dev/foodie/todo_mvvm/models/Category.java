package dev.foodie.todo_mvvm.models;

public class Category {

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
