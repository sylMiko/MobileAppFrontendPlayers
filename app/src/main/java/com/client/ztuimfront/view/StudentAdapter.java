package com.client.ztuimfront.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.client.ztuimfront.R;
import com.client.ztuimfront.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

    private final List<Student> studentList;
    private final ItemClickListener mItemListener;

    public StudentAdapter(List<Student> studentList, ItemClickListener itemClickListener) {
        this.studentList = studentList;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.student_item, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = studentList.get(position);
        holder.firstName.setText(student.getFirstName());
        holder.lastName.setText(student.getLastName());
        holder.email.setText(student.getEmail());
        holder.date.setText(student.getDateOfBirth());
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(studentList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public interface ItemClickListener {

        void onItemClick(Student student);
    }
}
