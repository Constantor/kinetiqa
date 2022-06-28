package bio.kinetiqa.android;

import android.net.Uri;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GraphInfoAdapter extends ArrayAdapter<Substance> {
    private LayoutInflater inflater;
    private int layout;
    private List<Substance> states;

    public GraphInfoAdapter(Context context, int resource, List<Substance> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.addPhoto);
        TextView nameView = view.findViewById(R.id.addName);
        TextView descriptionView = view.findViewById(R.id.graphDescription);

        Substance state = states.get(position);
        Glide.with(convertView).load(state.getImageResource()).placeholder(R.drawable.test_photo).into(flagView);

        nameView.setText(state.getName());
        descriptionView.setText(state.getDescription());

        Switch graphSwitch = view.findViewById(R.id.addSwitch);
        graphSwitch.setChecked(DataBase.Methods.graphStatus(state.getResourceID()));
        graphSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DataBase.Methods.addSubstanceOnGraph(state);
                } else {
                    DataBase.Methods.deleteSubstanceFromGraph(state);
                }
            }
        });

        return view;
    }

/*    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "Отслеживание переключения: " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();
    }*/
}