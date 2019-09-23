package dev.foodie.todo_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

    int all = 0, work = 0, music = 0, travel = 0, study = 0, home = 0, games = 0, shopping = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Retrieving your tasks...");
        progressDialog.show();

        initCategories();
        progressDialog.dismiss();

    }

    private void initCategories() {
        categories = new ArrayList<>();

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
