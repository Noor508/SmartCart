package com.example.devilapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.devilapplication.databinding.ActivityRegistrationBinding;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binder;
    private Spinner spinner;
    private TextView selectedItemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder= ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        //setContentView(R.layout.activity_registration);

//        spinner = findViewById(R.id.spinnerCity);
//        selectedItemText = findViewById(R.id.selectedItem);

        // Create a list of items for the spinner
        List<String> items = new ArrayList<>();
        items.add("Lahore");
        items.add("Multan");
        items.add("Okara");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        binder.spinnerCity.setAdapter(adapter);

        // Set up a listener to capture the selected item
        binder.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Display the selected item in the TextView
                binder.selectedItem.setText("Selected :" + items.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });


        // Button existingRegister = findViewById(R.id.existingText);
        binder.existingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        // Handle form submission (e.g., when a "Submit" button is clicked)
        // Button registerButton = findViewById(R.id.registerButton);
        binder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Post user input from EditText fields
//                EditText nameEditText = findViewById(R.id.fullname);
//                EditText emailEditText = findViewById(R.id.email);
//                EditText phoneEditText = findViewById(R.id.phone);
                //EditText cityEditText = findViewById(R.id.city);

                String name = binder.fullname.getText().toString();
                String email = binder.email.getText().toString();
                String phoneNumber = binder.phone.getText().toString();
                //    String city = binder.city.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phoneNumber)  ) {
                    // Display an error message or toast indicating empty fields
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Customer Registered !",Toast.LENGTH_SHORT).show();
                }



                // Clear the EditText fields
                binder.fullname.setText("");
                binder.email.setText("");
                binder.phone.setText("");
                //  cityEditText.setText("");
            }
        });
    }
}