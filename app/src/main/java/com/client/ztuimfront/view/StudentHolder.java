package com.client.ztuimfront.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.client.ztuimfront.R;

public class StudentHolder extends RecyclerView.ViewHolder {

    TextView firstName, lastName, email, date;

    public StudentHolder(@NonNull View itemView) {
        super(itemView);

        firstName = itemView.findViewById(R.id.employeeListItem_name);
        lastName = itemView.findViewById(R.id.employeeListItem_lastName);
        email = itemView.findViewById(R.id.employeeListItem_email);
        date = itemView.findViewById(R.id.employeeListItem_date);
    }
}
