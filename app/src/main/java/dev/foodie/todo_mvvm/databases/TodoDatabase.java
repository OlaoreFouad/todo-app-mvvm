package dev.foodie.todo_mvvm.databases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import dev.foodie.todo_mvvm.dao.TodoDao;
import dev.foodie.todo_mvvm.models.Todo;
import dev.foodie.todo_mvvm.tasks.Tasks;

@Database(entities = {
        Todo.class
}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    public static TodoDatabase instance;
    public abstract TodoDao getDao();

    public static TodoDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application.getApplicationContext(), TodoDatabase.class, "todos_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(dbCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Tasks.PopulateTodos(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

}
