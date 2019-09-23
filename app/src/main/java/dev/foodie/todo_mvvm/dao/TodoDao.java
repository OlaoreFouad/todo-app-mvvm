package dev.foodie.todo_mvvm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.foodie.todo_mvvm.models.Todo;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Query("DELETE FROM todos_table")
    void deleteAllTodos();

    @Query("SELECT * FROM todos_table WHERE category = :category")
    LiveData<List<Todo>> getTodosByCategory(String category);

    @Query("SELECT * FROM todos_table")
    LiveData<List<Todo>> getTodos();

    @Query("UPDATE todos_table SET completed = 1 WHERE id = :id")
    void setCompleted(String id);

    @Query("UPDATE todos_table SET completed = 0 WHERE id = :id")
    void setUncompleted(String id);

    @Query("UPDATE todos_table SET completed = 10 WHERE id = :id")
    void setLate(String id);

}
