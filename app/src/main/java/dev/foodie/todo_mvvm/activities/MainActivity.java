package dev.foodie.todo_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dev.foodie.todo_mvvm.R;
import dev.foodie.todo_mvvm.adapters.CategoryAdapter;
import dev.foodie.todo_mvvm.models.Category;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainListRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCategories();
        mainListRecyclerView = findViewById(R.id.mainListsRecyclerView);
        categoryAdapter = new CategoryAdapter(categories);

        mainListRecyclerView.setHasFixedSize(true);
        mainListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mainListRecyclerView.setAdapter(categoryAdapter);

    }

    private void initCategories() {
        categories = new ArrayList<>();

        categories.add(new Category(R.drawable.all, "All", 0));
        categories.add(new Category(R.drawable.work, "Work", 0));
        categories.add(new Category(R.drawable.music, "Music", 0));
        categories.add(new Category(R.drawable.travel, "Travel", 0));
        categories.add(new Category(R.drawable.study, "Study", 0));
        categories.add(new Category(R.drawable.home, "Home", 0));
        categories.add(new Category(R.drawable.games, "Games", 0));
        categories.add(new Category(R.drawable.shop, "Shopping", 0));
    }
}
