package com.example.devilapplication.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devilapplication.R;
import com.example.devilapplication.Room.UserDao;
import com.example.devilapplication.Room.UserDatabase;
import com.example.devilapplication.Room.Users;

public class UpdateActivity extends AppCompatActivity {
    private EditText editName, editPrice;
    private Button updateButton;

    private Users users;
    private UserDatabase userDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editPrice = findViewById(R.id.price);
        editName = findViewById(R.id.name);
        updateButton = findViewById(R.id.update);

        userDatabase=UserDatabase.getInstance(this);
        userDao=userDatabase.getDao();

        users=(Users) getIntent().getSerializableExtra("model");

        editName.setText(users.getName());
        editPrice.setText(users.getPrice());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users userModel = new Users(users.getId(),editName.getText().toString(), editPrice.getText().toString());
                userDao.update(userModel);
                Toast.makeText(getApplicationContext(), "UPDATED!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
