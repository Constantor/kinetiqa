package bio.kinetiqa.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {

    private Substance substance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        int subID = (int) getIntent().getSerializableExtra("state");
        substance = DataBase.getSubstanceFromId(subID);

        ImageView flagView = findViewById(R.id.photo);
        TextView nameView = findViewById(R.id.nameDesc);
        TextView capitalView = findViewById(R.id.longDescription);

        flagView.setImageResource(substance.getImageResource());
        nameView.setText(substance.getName());
        capitalView.setText(substance.getDescription());


    }

    public void addMedicine(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Запись о приёме добавлена", Toast.LENGTH_SHORT);
        toast.show();
        DataBase.addTakeMedicine(substance);
    }

    public void deleteSubstance(View view) {
        DataBase.deleteUserSubstance(substance);
        finish();
    }
}