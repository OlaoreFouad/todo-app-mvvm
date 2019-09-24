package dev.foodie.todo_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dev.foodie.todo_mvvm.R;
import dev.foodie.todo_mvvm.adapters.CategoryAdapter;
import dev.foodie.todo_mvvm.listeners.OnCategorySelectedListener;
import dev.foodie.todo_mvvm.models.Category;
import dev.foodie.todo_mvvm.models.Todo;
import dev.foodie.todo_mvvm.viewmodels.TodoViewModel;

public class MainActivity extends AppCompatActivity implements OnCategorySelectedListener {

    private RecyclerView mainListRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    private TodoViewModel todoViewModel;
    private ProgressDialog progressDialog;
    private FloatingActionButton deleteTodosFab;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    int all = 0, work = 0, music = 0, travel = 0, study = 0, home = 0, games = 0, shopping = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        Log.d("TODOS", "onCreate: " + todoViewModel.hashCode());
        initCategories();

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshContainer);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryAdapter.clearList();
                initCategories();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        deleteTodosFab = findViewById(R.id.deleteTodosFab);

        deleteTodosFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete All Todos")
                        .setMessage("Are you sure you don't have anything to do?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("I'm sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                todoViewModel.deleteAllTodos();
                                categoryAdapter.clearList();
                                initCategories();
                            }
                        }).create();

                alertDialog.show();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Retrieving your tasks...");
        progressDialog.show();


        progressDialog.dismiss();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initCategories() {
        categories = new ArrayList<>();
        all = 0; work = 0; music = 0; travel = 0; study = 0; home = 0; games = 0; shopping = 0;

        todoViewModel.getTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                Log.d("TODOS", "onChanged: hey!" + todos.size());
                for (Todo todo: todos) {
                    switch (todo.getCategory().toLowerCase()) {
                        case "all": ++all; break;
                        case "work": ++work; break;
                        case "music": ++music; break;
                        case "travel": ++travel; break;
                        case "study": ++study; break;
                        case "home": ++home; break;
                        case "games": ++games; break;
                        case "shopping": ++shopping; break;
                    }
                }

                categories.add(new Category(R.drawable.all, "All", all));
                categories.add(new Category(R.drawable.work, "Work", work));
                categories.add(new Category(R.drawable.music, "Music", music));
                categories.add(new Category(R.drawable.travel, "Travel", travel));
                categories.add(new Category(R.drawable.study, "Study", study));
                categories.add(new Category(R.drawable.home, "Home", home));
                categories.add(new Category(R.drawable.games, "Games", games));
                categories.add(new Category(R.drawable.shop, "Shopping", shopping));

                mainListRecyclerView = findViewById(R.id.mainListsRecyclerView);
                categoryAdapter = new CategoryAdapter(categories, MainActivity.this);

                mainListRecyclerView.setHasFixedSize(true);
                mainListRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                mainListRecyclerView.setAdapter(categoryAdapter);

            }
        });
    }

    @Override
    public void categorySelected(final Category category) throws Exception {
        Intent intent = new Intent(MainActivity.this, ViewTodosActivity.class);
        intent.putExtra("title", category.getTitle());
        intent.putExtra("image", category.getImageName());

        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
