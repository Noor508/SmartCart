package com.example.devilapplication.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devilapplication.Activities.UpdateActivity;
import com.example.devilapplication.Adapter.AdapterListener;
import com.example.devilapplication.Adapter.UserAdapter;
import com.example.devilapplication.R;

import java.util.List;

public class CrudActivity extends AppCompatActivity implements AdapterListener {

    EditText editName, editPrice;
    Button insertButton, deleteAllButton;
    RecyclerView myrecycler;

    private UserDatabase userDatabase;
    private UserDao userDao;

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        deleteAllButton = findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAllConfirmationDialog();
            }
        });

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userAdapter = new UserAdapter(this, this);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        editName = findViewById(R.id.name);
        editPrice = findViewById(R.id.price);
        insertButton = findViewById(R.id.insert);
        myrecycler = findViewById(R.id.usersRecycler);

        myrecycler.setAdapter(userAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String priceStr = editPrice.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(priceStr)) {
                    try {
                        Users users = new Users(0, name, priceStr);
                        userViewModel.insert(users);

                        editName.setText("");
                        editPrice.setText("");

                        Toast.makeText(CrudActivity.this, "Inserted. Price: " + priceStr, Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(CrudActivity.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CrudActivity.this, "Please enter a name and price", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetchData();
    }

    private void fetchData() {
        userViewModel.getAllUsers().observe(this, users -> {
            userAdapter.setUsersList(users);
        });
    }

    @Override
    public void OnUpdate(Users users) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("model", users);
        startActivity(intent);
    }

    @Override
    public void OnDelete(int id, int pos) {
        showDeleteConfirmationDialog(id);
    }

    private void showDeleteConfirmationDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this item?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked Yes, proceed with the deletion
                userViewModel.delete(id);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No, do nothing
            }
        });

        builder.show();
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(userDao).execute();
    }

    private void showDeleteAllConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete All");
        builder.setMessage("Are you sure you want to delete all records?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked Yes, proceed with deleting all records
                deleteAll();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No, do nothing
            }
        });

        builder.show();
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        DeleteAllAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }
}
