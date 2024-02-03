package com.example.devilapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.devilapplication.API.GET.Display_API;
import com.example.devilapplication.API.POST.Display_POST;
import com.example.devilapplication.Fragments.DynamicFragment;
import com.example.devilapplication.Fragments.upperFragment;
import com.example.devilapplication.R;
import com.example.devilapplication.RecyclerView.Item_Product;
import com.example.devilapplication.Room.CrudActivity;

public class HomeActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "SMARTCART";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnStaticFragment = findViewById(R.id.showStatic);
        Button btnApiRequest = findViewById(R.id.get);
        Button btnApiResponse = findViewById(R.id.post);
        Button btnDynamicFragment = findViewById(R.id.DFragmentButton);
        Button showNotificationButton = findViewById(R.id.show_notification_button);

        btnStaticFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new upperFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnApiRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Display_API.class);
                startActivity(intent);
            }
        });

        btnApiResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Display_POST.class);
                startActivity(intent);
            }
        });

        btnDynamicFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DynamicFragment.class);
                startActivity(intent);
            }
        });

        createNotificationChannel();

        showNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    public void onclickUserDb(View view) {
        Intent intent = new Intent(this, CrudActivity.class);
        startActivity(intent);
    }

    public void onclickcalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void onclickform(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void onclickstaticfragment(View view) {
        Intent intent = new Intent(this, upperFragment.class);
        startActivity(intent);
    }

    public void onClickGet(View view) {
        Intent intent = new Intent(HomeActivity.this, Display_API.class);
        startActivity(intent);
    }

    public void onClickPost(View view) {
        Intent intent = new Intent(HomeActivity.this, Display_POST.class);
        startActivity(intent);
    }

    public void showCustomDialog(View view) {
        showCustomDialog();
    }

    public void onclickitem(View view) {
        Intent intent = new Intent(HomeActivity.this, Item_Product.class);
        startActivity(intent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "SmartCart";
            String description = "Online E-commerce App";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Smartcart")
                .setContentText("12.12 sale 50% off")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    public void onClickShowDialog(View view) {
        showDefaultDialog();
    }

    public void onClickShowDefaultDialog(View view) {
        showDefaultDialog();
    }

    public void showDefaultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SmartCart App")
                .setMessage("Do you want to exit?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void onClickShowCustomDialog(View view) {
        showCustomDialog();
    }

    public void showCustomDialog() {
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.custom_dialog_layout);

        Button closeButton = customDialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        customDialog.show();
    }
}
