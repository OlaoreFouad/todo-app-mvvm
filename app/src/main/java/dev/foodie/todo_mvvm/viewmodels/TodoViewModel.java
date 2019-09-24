package dev.foodie.todo_mvvm.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dev.foodie.todo_mvvm.models.Todo;
import dev.foodie.todo_mvvm.repositories.TodoRepository;

public class TodoViewModel extends AndroidViewModel {

    private TodoRepository todoRepository;
    private LiveData<List<Todo>> todos;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        todoRepository = new TodoRepository(application);
        todos = todoRepository.getTodos();
    }

    public void addTodo(Todo todo) {
        this.todoRepository.addTodo(todo);
    }

    public void updateTodo(Todo todo) {
        this.todoRepository.updateTodo(todo);
    }

    public void deleteTodo(Todo todo) {
        this.todoRepository.deleteTodo(todo);
    }

    public void deleteAllTodos() {
        this.todoRepository.deleteAllTodos();
    }

    public void setCompletedTodo(String id) {
        this.todoRepository.setCompleted(id);
    }

    public void setUncompletedTodo(String id) {
        this.todoRepository.setUncompleted(id);
    }

    public void setLateTodo(String id) {
        this.todoRepository.setLate(id);
    }

    public LiveData<List<Todo>> getTodosByCategory(String category) {
        Log.d("TODOS", "getTodosByCategory: " + category);
        return this.todoRepository.getTodosByCategory(category);
    }

    public LiveData<List<Todo>> getTodos() {
        return todos;
    }
}
