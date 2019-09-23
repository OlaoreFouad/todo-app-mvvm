package dev.foodie.todo_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dev.foodie.todo_mvvm.R;

public class AddTodoActivity extends AppCompatActivity {

    private ImageView closeActivity;
    private EditText todoTitle, todoDescription;
    private TextView todoDateTime, todoCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        closeActivity = findViewById(R.id.closeActivityButton);
        todoTitle = findViewById(R.id.addTodoTitle);
        todoDescription = findViewById(R.id.addTodoDescription);
        todoDateTime = findViewById(R.id.addTodoDateTime);
        todoCategory = findViewById(R.id.addTodoCategory);

        Bundle extras = getIntent().getExtras();

        todoCategory.setText(extras.getString("category"));
        todoDateTime.setText(new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis() + 600000)));

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int hour = datePicker.getDayOfMonth();
                int minute = datePicker.getYear();

                todoDateTime.setText(hour + ":" + minute);
            }
        });

        todoDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
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
