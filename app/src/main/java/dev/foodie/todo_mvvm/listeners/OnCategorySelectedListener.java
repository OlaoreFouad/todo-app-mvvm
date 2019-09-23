package dev.foodie.todo_mvvm.listeners;

import dev.foodie.todo_mvvm.models.Category;

public interface OnCategorySelectedListener {

    void categorySelected(Category category) throws Exception;

}
