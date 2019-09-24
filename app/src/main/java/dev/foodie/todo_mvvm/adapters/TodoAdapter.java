package dev.foodie.todo_mvvm.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.foodie.todo_mvvm.R;
import dev.foodie.todo_mvvm.listeners.OnTodoCompletedListener;
import dev.foodie.todo_mvvm.models.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todos;
    private OnTodoCompletedListener mOnTodoCompletedListener;

    public TodoAdapter(OnTodoCompletedListener mOnTodoCompletedListener) {
        this.mOnTodoCompletedListener = mOnTodoCompletedListener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.setData(todo);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView title, datetime;
        CheckBox completed;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.todoTitle);
            datetime = itemView.findViewById(R.id.todoDateTime);
            completed = itemView.findViewById(R.id.todoCompleted);

        }

        public void setData(Todo todo) {
            this.title.setText(todo.getTitle());

            String pattern = "hh:mm - MMM dd";

            this.datetime.setText(new SimpleDateFormat(pattern).format(new Date(todo.getCreatedAt())));
            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        Todo completedTodo = todos.get(getAdapterPosition());
                        mOnTodoCompletedListener.todoCompleted(completedTodo);
                    }
                }
            });
        }
    }

}
