package bio.kinetiqa.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);

        ArrayList<Substance> products;
        products = DataBase.Methods.getMainSubstanceBase();
        ListView productList = findViewById(R.id.addSubList);
        AddSubstanceAdapter adapter = new AddSubstanceAdapter(this, R.layout.add_sub_item, products);
        productList.setAdapter(adapter);
    }

    public void addSubstance(View view) {
        finish();
    }
}