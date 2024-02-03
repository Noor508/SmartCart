package com.example.devilapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.devilapplication.R;

public class SignupActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        Button signupButton = findViewById(R.id.signupButton);
        TextView existingText = findViewById(R.id.existingText);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        signupButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                // Save the user data to SharedPreferences
                String userKey = "user_" + username;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(userKey + "_username", username);
                editor.putString(userKey + "_email", email);
                editor.putString(userKey + "_password", password);
                editor.apply();

                Toast.makeText(SignupActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();

                // Navigate to the login activity
                Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish(); // Optional: finish the current activity to prevent going back to it with the back button
            }
        });

        existingText.setOnClickListener(v -> {
            Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish(); // Optional: finish the current activity to prevent going back to it with the back button
        });
    }
}
