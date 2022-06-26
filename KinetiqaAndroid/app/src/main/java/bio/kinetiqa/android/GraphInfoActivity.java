package bio.kinetiqa.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class GraphInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_info);

        ArrayList<Substance> products = (ArrayList<Substance>) DataBase.getListOfSubstances();
        ListView productList = findViewById(R.id.addSubList);
        GraphInfoAdapter adapter = new GraphInfoAdapter(this, R.layout.graph_info_elem, products);
        productList.setAdapter(adapter);
    }

    public void close(View view) {
        //TODO(update of user list)
        finish();
    }
}