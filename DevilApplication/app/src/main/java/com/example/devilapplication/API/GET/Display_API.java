package com.example.devilapplication.API.GET;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.devilapplication.R;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class Display_API extends AppCompatActivity {
//    private ListView listView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_api);
//
//        listView = findViewById(R.id.listviewData);
//
//        loadDataFromAPI();
//    }
//
//    private void loadDataFromAPI() {
//        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
//        Call<Model> call = methods.getAllData();
//
//        call.enqueue(new Callback<Model>() {
//            @Override
//            public void onResponse(Call<Model> call, Response<Model> response) {
//                if (response.isSuccessful()) {
//                    ArrayList<Model.Data> data = response.body().getData();
//                    String[] names = new String[data.size()];
//                    for (int i = 0; i < data.size(); i++) {
//                        names[i] = "Id: " + data.get(i).getId() +
//                                "\nName: " + data.get(i).getName() +
//                                "\nYear: " + data.get(i).getYear() +
//                                "\nColor: " + data.get(i).getColor() +
//                                "\nPantone Value: " + data.get(i).getPantone_value();
//                    }
//                    listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, names));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Request not successful", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Model> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}





import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class Display_API extends AppCompatActivity {
    private ListView listView;
    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_api);

        listView = findViewById(R.id.listviewData);

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        viewModel.getData().observe(this, new Observer<ArrayList<Model.Data>>() {
            @Override
            public void onChanged(ArrayList<Model.Data> data) {
                updateListView(data);
            }
        });

        viewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.loadDataFromAPI();
    }

    private void updateListView(ArrayList<Model.Data> data) {
        // Create an ArrayList<String> to store the formatted data for the ListView
        ArrayList<String> dataList = new ArrayList<>();

        // Format the data for display
        for (Model.Data item : data) {
            String formattedData = "Id: " + item.getId() +
                    "\nName: " + item.getName() +
                    "\nYear: " + item.getYear() +
                    "\nColor: " + item.getColor() +
                    "\nPantone Value: " + item.getPantone_value();
            dataList.add(formattedData);
        }

        // Use an ArrayAdapter to update the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}

