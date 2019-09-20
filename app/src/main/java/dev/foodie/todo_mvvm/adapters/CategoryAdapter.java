package dev.foodie.todo_mvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.foodie.todo_mvvm.R;
import dev.foodie.todo_mvvm.models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;

    public CategoryAdapter(List<Category> categories){
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_category_item, parent, false);

        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category cat = this.categories.get(position);
        holder.setData(cat);
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title, tasks;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.categoryImage);
            title = itemView.findViewById(R.id.categoryTitle);
            tasks = itemView.findViewById(R.id.categoryTasks);
        }

        void setData(Category cat) {
            this.image.setImageResource(cat.getImageName());
            this.title.setText(cat.getTitle());
            this.tasks.setText(cat.getTasks() + " Tasks");
        }

    }

}
