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
        String desc1 = "Ибупрофен применяется как обезболивающее и противовоспалительное средство при лечении ревматоидного артрита, анкилозирующего спондилита, остеоартроза и других неревматоидных артропатий.";
        products.add(new Substance("Нурофен", desc1, R.drawable.nurofen, 5));
        ListView productList = findViewById(R.id.addSubList);
        GraphInfoAdapter adapter = new GraphInfoAdapter(this, R.layout.graph_info_elem, products);
        productList.setAdapter(adapter);
    }

    public void close(View view) {
        //TODO(update of user list)
        finish();
    }
}