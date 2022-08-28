package com.client.ztuimfront.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class StudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        initializeComponent();
    }

    private void initializeComponent() {

        TextInputEditText inputEditFirstName = findViewById(R.id.update_textFieldFirstName);
        TextInputEditText inputEditLastName = findViewById(R.id.update_textFieldLastName);
        TextInputEditText inputEditEmail = findViewById(R.id.update_textFieldEmail);
        TextInputEditText inputEditDate = findViewById(R.id.update_textFieldDateOfBirth);
        Button buttonUpdate = findViewById(R.id.update_buttonUpdate);
        Button buttonDelete = findViewById(R.id.update_buttonDelete);

        RetrofitService retrofitService = new RetrofitService();
        StudentApi studentApi = retrofitService.getRetrofit().create(StudentApi.class);

        Student student = getIntent().getParcelableExtra("student");
        inputEditFirstName.setText(student.getFirstName());
        inputEditLastName.setText(student.getLastName());
        inputEditEmail.setText(student.getEmail());
        inputEditDate.setText(student.getDateOfBirth());

        buttonUpdate.setOnClickListener(view -> {

            String firstName = String.valueOf(inputEditFirstName.getText());
            String lastName = String.valueOf(inputEditLastName.getText());
            String email = String.valueOf(inputEditEmail.getText());
            String date = String.valueOf(inputEditDate.getText());

            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setDateOfBirth(date);

            studentApi.update(student, student.getId()).enqueue(new Callback<Student>() {
                @Override
                public void onResponse(@NonNull Call<Student> call, @NonNull Response<Student> response) {
                    Toast.makeText(StudentDetails.this, "Save successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(StudentDetails.this, StudentListActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(@NonNull Call<Student> call, @NonNull Throwable t) {
                    Toast.makeText(StudentDetails.this, "Save unsuccessful!", Toast.LENGTH_LONG).show();
                }
            });
        });

        buttonDelete.setOnClickListener(view -> {
            studentApi.delete(student.getId()).enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) {
                    Toast.makeText(StudentDetails.this, "Delete successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(StudentDetails.this, StudentListActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Logger.getLogger(StudentDetails.class.getName()).log(Level.SEVERE, "Blad dotyczacy usuwania");
                }
            });
        });
    }
}