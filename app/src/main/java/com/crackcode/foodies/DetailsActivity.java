package com.crackcode.foodies;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.crackcode.foodies.Db.DBHelper;
import com.crackcode.foodies.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) ==1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String foodname = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.priceText.setText(String.format("%d", price));
            binding.nameTextView.setText(foodname);
            binding.detailsDesc.setText(description);

            binding.orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.editTextPersonName.getText().toString(),
                            binding.editTextPhone.getText().toString(),
                            price,
                            image,
                            foodname,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(DetailsActivity.this, "Order placed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailsActivity.this, "Order is not place", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            final int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.priceText.setText(String.format("%d", cursor.getInt(3)));
            binding.nameTextView.setText(cursor.getString(7));
            binding.detailsDesc.setText(cursor.getString(5));

            binding.editTextPersonName.setText(cursor.getString(1));
            binding.editTextPhone.setText(cursor.getString(2));
            binding.orderButton.setText("Update Now");

            binding.orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(
                            binding.editTextPersonName.getText().toString(),
                            binding.editTextPhone.getText().toString(),
                            Integer.parseInt(binding.priceText.getText().toString()),
                            image,
                            binding.detailsDesc.getText().toString(),
                            binding.nameTextView.getText().toString(),
                            1,
                            id
                    );
                    if (isUpdated) {
                        Toast.makeText(DetailsActivity.this, "Order updated", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(DetailsActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}