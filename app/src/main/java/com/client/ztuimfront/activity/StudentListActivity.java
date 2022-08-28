package com.client.ztuimfront.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.ztuimfront.R;
import com.client.ztuimfront.model.Student;
import com.client.ztuimfront.service.RetrofitService;
import com.client.ztuimfront.service.StudentApi;
import com.client.ztuimfront.view.StudentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        recyclerView = findViewById(R.id.studentList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton floatingActionButton = findViewById(R.id.studentList_button);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        loadStudents();
    }

    private void loadStudents() {

        RetrofitService retrofitService = new RetrofitService();
        StudentApi studentApi = retrofitService.getRetrofit().create(StudentApi.class);
        studentApi.getAllStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(@NonNull Call<List<Student>> call, @NonNull Response<List<Student>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Student>> call, @NonNull Throwable t) {
                Toast.makeText(StudentListActivity.this, "Blad jaki", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateListView(List<Student> studentList) {
        StudentAdapter studentAdapter = new StudentAdapter(studentList, student -> {
            showToast(student.getId() + "Clicked");
            Intent intent = new Intent(StudentListActivity.this, StudentDetails.class);
            intent.putExtra("student", student);
            startActivity(intent);
        });
        recyclerView.setAdapter(studentAdapter);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}