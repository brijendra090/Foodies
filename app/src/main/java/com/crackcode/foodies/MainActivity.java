package com.crackcode.foodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crackcode.foodies.Adapters.MainAdapter;
import com.crackcode.foodies.Models.MainModel;
import com.crackcode.foodies.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.blackberry_vanilla_french_toast, "Toast", "5", "Blackberry vanilla french toast"));
        list.add(new MainModel(R.drawable.roasted_chiken, "Roasted Chicken", "10", "Roast Chicken slathered with a garlic-herb-lemon butter then oven roasted to golden crispy perfection"));
        list.add(new MainModel(R.drawable.vitamin_d_pizza, "Pizza", "8", "Some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly"));
        list.add(new MainModel(R.drawable.avocado_blackbean_eggs, "Avocado Blackbean Eggs", "12", "This huevos rancheros recipe features spicy black beans and fresh"));
        list.add(new MainModel(R.drawable.vegan_creamy_mushroom_ramen_close, "Vegan Mushroom", "7", "Frittatas lend vegan creamy mushroom ramen close meals."));
        list.add(new MainModel(R.drawable.balsamic_beans_noodles, "Beans Noodles", "5", "This Pasta and Beans with Balsamic Roasted Veggies and you’ll want to make it all summer long."));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderMainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}