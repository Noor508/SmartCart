package com.example.devilapplication.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devilapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Item_Product extends AppCompatActivity {

    private List<Product> productList;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView; // Declare recyclerView at the class level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_items);

        initData();

        recyclerView = findViewById(R.id.recyclerView); // Initialize recyclerView

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(productAdapter);
    }

    private void initData() {
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.shoe, "$19.99", "High-quality product"));
        productList.add(new Product(R.drawable.makeup, "$29.99", "Affordable and stylish"));
        productList.add(new Product(R.drawable.laptop, "$1200.99", "Efficient and Affordable"));
        productList.add(new Product(R.drawable.iphone, "$2200.99", "15 pro"));


    }

    public void onProductItemClick(View view) {
        int position = recyclerView.getChildAdapterPosition(view); // Call on recyclerView
        Product product = productList.get(position);

        // Display product details or perform any action on item click
        String details = "Price: " + product.getPrice() + "\nDescription: " + product.getDescription();
        Toast.makeText(this, details, Toast.LENGTH_SHORT).show();
    }
}
