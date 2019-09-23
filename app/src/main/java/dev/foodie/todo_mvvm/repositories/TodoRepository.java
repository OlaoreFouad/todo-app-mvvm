package dev.foodie.todo_mvvm.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import dev.foodie.todo_mvvm.dao.TodoDao;
import dev.foodie.todo_mvvm.databases.TodoDatabase;
import dev.foodie.todo_mvvm.models.Todo;
import dev.foodie.todo_mvvm.tasks.Tasks;

public class TodoRepository {
    private TodoDao dao;
    private TodoDatabase todoDatabase;
    private LiveData<List<Todo>> todos;

    public TodoRepository(Application application) {
        todoDatabase = TodoDatabase.getInstance(application);
        dao = todoDatabase.getDao();
        todos = dao.getTodos();
    }

    public void addTodo(Todo todo) {
        new Tasks.AddTodo(dao).execute(todo);
    }

    public void updateTodo(Todo todo) {
        new Tasks.UpdateTodo(dao).execute(todo);
    }

    public void deleteTodo(Todo todo) {
        new Tasks.DeleteTodo(dao).execute(todo);
    }

    public void deleteAllTodos() {
        new Tasks.DeleteAllTodos(dao).execute();
    }

    public void setCompleted(String id) {
        new Tasks.SetTodoCompleted(dao).execute(id);
    }

    public void setUncompleted(String id) {
        new Tasks.SetTodoUncompleted(dao).execute(id);
    }

    public void setLate(String id) {
        new Tasks.SetTodoLate(dao).execute(id);
    }

    public LiveData<List<Todo>> getTodosByCategory(String category) throws Exception {
        return new Tasks.GetTodosByCategory(dao).execute(category).get();
    }

    public LiveData<List<Todo>> getTodos() {
        return todos;
    }

}
