package com.client.ztuimfront.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.client.ztuimfront.R;
import com.client.ztuimfront.model.Student;
import com.client.ztuimfront.service.RetrofitService;
import com.client.ztuimfront.service.StudentApi;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {

        TextInputEditText inputEditFirstName = findViewById(R.id.form_textFieldFirstName);
        TextInputEditText inputEditLastName = findViewById(R.id.form_textFieldLastName);
        TextInputEditText inputEditEmail = findViewById(R.id.form_textFieldEmail);
        TextInputEditText inputEditDate = findViewById(R.id.form_textFieldDateOfBirth);
        Button buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        StudentApi studentApi = retrofitService.getRetrofit().create(StudentApi.class);

        buttonSave.setOnClickListener(view -> {

            String firstName = String.valueOf(inputEditFirstName.getText());
            String lastName = String.valueOf(inputEditLastName.getText());
            String email = String.valueOf(inputEditEmail.getText());
            String date = String.valueOf(inputEditDate.getText());

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setDateOfBirth(date);

            studentApi.save(student).enqueue(new Callback<Student>() {

                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {
                    Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Student> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save unsuccessful!", Toast.LENGTH_LONG).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Bl;ad jest i ch7uj");
                }
            });
            //maybe add unqueue and onResponse/onFailure method
        });
    }
}