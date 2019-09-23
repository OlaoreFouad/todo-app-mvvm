package dev.foodie.todo_mvvm.tasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import dev.foodie.todo_mvvm.dao.TodoDao;
import dev.foodie.todo_mvvm.databases.TodoDatabase;
import dev.foodie.todo_mvvm.models.Todo;

public class Tasks {

    public static class AddTodo extends AsyncTask<Todo, Void, Void> {

        TodoDao dao;

        public AddTodo(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            this.dao.addTodo(todos[0]);
            return null;
        }
    }

    public static class UpdateTodo extends AsyncTask<Todo, Void, Void> {

        TodoDao dao;

        public UpdateTodo(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            this.dao.updateTodo(todos[0]);
            return null;
        }
    }

    public static class DeleteTodo extends AsyncTask<Todo, Void, Void> {

        TodoDao dao;

        public DeleteTodo(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            this.dao.deleteTodo(todos[0]);
            return null;
        }
    }

    public static class DeleteAllTodos extends AsyncTask<Void, Void, Void> {

        TodoDao dao;

        public DeleteAllTodos(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.dao.deleteAllTodos();
            return null;
        }
    }

    public static class SetTodoCompleted extends AsyncTask<String, Void, Void> {

        TodoDao dao;

        public SetTodoCompleted(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            this.dao.setCompleted(strings[0]);
            return null;
        }
    }

    public static class SetTodoUncompleted extends AsyncTask<String, Void, Void> {

        TodoDao dao;

        public SetTodoUncompleted(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            this.dao.setUncompleted(strings[0]);
            return null;
        }
    }

    public static class GetTodosByCategory extends AsyncTask<String, Void, LiveData<List<Todo>>> {

        TodoDao dao;

        public GetTodosByCategory(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Todo>> doInBackground(String... strings) {
            return this.dao.getTodosByCategory(strings[0]);
        }
    }

    public static class SetTodoLate extends AsyncTask<String, Void, Void> {

        TodoDao dao;

        public SetTodoLate(TodoDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            this.dao.setLate(strings[0]);
            return null;
        }
    }

    public static class PopulateTodos extends AsyncTask<Void, Void, Void> {

        TodoDao dao;

        public PopulateTodos(TodoDatabase db) {
            this.dao = db.getDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.dao.addTodo(new Todo(UUID.randomUUID().toString(), "Practice Piano",
                    "I have to learn it since it is important", System.currentTimeMillis(), "all"));
            this.dao.addTodo(new Todo(UUID.randomUUID().toString(), "Practice Violin",
                    "I have to learn it since it is important", System.currentTimeMillis(), "work"));
            this.dao.addTodo(new Todo(UUID.randomUUID().toString(), "Type code",
                    "I have to learn it since it is important", System.currentTimeMillis(), "music"));
            this.dao.addTodo(new Todo(UUID.randomUUID().toString(), "Play Games",
                    "I have to learn it since it is important", System.currentTimeMillis(), "all"));
            return null;
        }
    }

}
