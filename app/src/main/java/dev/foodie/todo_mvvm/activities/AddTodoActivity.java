package dev.foodie.todo_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import dev.foodie.todo_mvvm.R;
import dev.foodie.todo_mvvm.models.Todo;

public class AddTodoActivity extends AppCompatActivity {

    private ImageView closeActivity;
    private EditText todoTitle, todoDescription;
    private TextView todoDateTime, todoCategory;
    private Button createTodoButton;

    private long createdAt;
    int day, month, hour, minute, year;

    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        closeActivity = findViewById(R.id.closeActivityButton);
        todoTitle = findViewById(R.id.addTodoTitle);
        todoDescription = findViewById(R.id.addTodoDescription);
        todoDateTime = findViewById(R.id.addTodoDateTime);
        todoCategory = findViewById(R.id.addTodoCategory);
        createTodoButton = findViewById(R.id.createTodoButton);

        Bundle extras = getIntent().getExtras();
        category = extras.getString("category");

        todoCategory.setText(category);
        todoDateTime.setText(new SimpleDateFormat("MMM dd, hh:mm").format(new Date(System.currentTimeMillis())));
        createdAt = System.currentTimeMillis();

        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute, 0);
                createdAt = calendar.getTimeInMillis();

                Log.d("TODOS", "onClick: " + new SimpleDateFormat("MMM dd, hh:mm").format(new Date(createdAt)));
                if (createdAt < System.currentTimeMillis()) {
                    createdAt = System.currentTimeMillis();
                    Toast.makeText(AddTodoActivity.this, "You can't set a todo for an earlier date", Toast.LENGTH_SHORT).show();
                } else {
                    todoDateTime.setText(new SimpleDateFormat("MMM dd, hh:mm").format(new Date(createdAt)));
                }
            }
        }, 1, 30, true);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth();
                year = datePicker.getYear();

                timePickerDialog.show();
            }
        });

        todoDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        createTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (
                        TextUtils.isEmpty(todoTitle.getText())
                ) {
                    Toast.makeText(AddTodoActivity.this, "What exactly are you todo-ing?", Toast.LENGTH_SHORT).show();
                    return;
                }

                String title = todoTitle.getText().toString();
                String description = todoDescription.getText().toString();

                Todo addedTodo = new Todo(UUID.randomUUID().toString(), title, description, createdAt, category);

                Intent intent = new Intent();
                intent.putExtra("addedTodo", addedTodo);

                setResult(RESULT_OK, intent);
                finish();

            }
        });

        closeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
