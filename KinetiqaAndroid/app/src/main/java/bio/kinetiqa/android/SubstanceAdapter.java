package bio.kinetiqa.android;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SubstanceAdapter extends ArrayAdapter<Substance> {
    private LayoutInflater inflater;
    private int layout;
    private List<Substance> states;

    public SubstanceAdapter(Context context, int resource, List<Substance> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView capitalView = view.findViewById(R.id.capital);

        Substance state = states.get(position);

        flagView.setImageResource(state.getImageResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getDescription());

        return view;
    }
}
