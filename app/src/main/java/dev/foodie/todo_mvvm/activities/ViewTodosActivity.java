package dev.foodie.todo_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dev.foodie.todo_mvvm.R;
import dev.foodie.todo_mvvm.adapters.TodoAdapter;
import dev.foodie.todo_mvvm.models.Todo;
import dev.foodie.todo_mvvm.viewmodels.TodoViewModel;

public class ViewTodosActivity extends AppCompatActivity {

    public static final String TAG = "TODOS";
    public static final int REQUEST_CODE = 1;
    private TextView viewCategoryTitle, viewCategoryTasks, noTodosContent, noTodosTitle;
    private ImageView viewCategoryImage, backArrow;

    private TodoViewModel todoViewModel;
    private RecyclerView todosRecyclerView;
    private TodoAdapter todoAdapter;

    private FloatingActionButton addTodoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todos);

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        viewCategoryTitle = findViewById(R.id.viewCategoryTitle);
        viewCategoryTasks = findViewById(R.id.viewCategoryTasks);
        viewCategoryImage = findViewById(R.id.viewCategoryImage);
        backArrow = findViewById(R.id.backArrow);
        noTodosTitle = findViewById(R.id.noTodosTitle);
        noTodosContent = findViewById(R.id.noTodosContent);
        todosRecyclerView = findViewById(R.id.todosList);
        addTodoButton = findViewById(R.id.addTodoFab);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTodoIntent = new Intent(ViewTodosActivity.this, AddTodoActivity.class);
                addTodoIntent.putExtra("category", viewCategoryTitle.getText().toString().toLowerCase());

                startActivityForResult(addTodoIntent, REQUEST_CODE);
            }
        });

        Bundle extras = getIntent().getExtras();

        String title = extras.getString("title");
        int image = extras.getInt("image");

        try {
            todoViewModel.getTodosByCategory(title.toLowerCase()).observe(this, new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todos) {
                    if (todos.isEmpty() || todos == null) {
                        noTodosTitle.setVisibility(View.VISIBLE);
                        noTodosContent.setVisibility(View.VISIBLE);
                        todosRecyclerView.setVisibility(View.INVISIBLE);
                    } else {
                        todoAdapter = new TodoAdapter();
                        todosRecyclerView.setHasFixedSize(true);
                        todosRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        todosRecyclerView.setAdapter(todoAdapter);

                        todoAdapter.setTodos(todos);
                        viewCategoryTasks.setText(todos.size() + " Tasks");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewCategoryTitle.setText(title);
        viewCategoryImage.setImageResource(image);
    }
}
