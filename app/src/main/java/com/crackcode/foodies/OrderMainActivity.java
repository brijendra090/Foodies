package com.crackcode.foodies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.crackcode.foodies.Adapters.OrdersAdapter;
import com.crackcode.foodies.Db.DBHelper;
import com.crackcode.foodies.Models.OrdersModel;
import com.crackcode.foodies.databinding.ActivityOrderMainBinding;

import java.util.ArrayList;

public class OrderMainActivity extends AppCompatActivity {

    ActivityOrderMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);
    }
}