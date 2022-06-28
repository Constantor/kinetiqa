package bio.kinetiqa.android;

import android.net.Uri;
import android.provider.ContactsContract;
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

import java.net.URI;
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

        ImageView flagView = view.findViewById(R.id.photo);
        TextView nameView = view.findViewById(R.id.name);
        TextView capitalView = view.findViewById(R.id.description);

        Substance state = states.get(position);
        Glide.with(view).load(state.getImageResource()).placeholder(R.drawable.test_photo).into(flagView);

        flagView.setImageURI(Uri.parse(state.getImageResource()));
        nameView.setText(state.getName());
        capitalView.setText(state.getDescription());

        Switch NotifySwitch = view.findViewById(R.id.notification_switch);
        NotifySwitch.setChecked(DataBase.Methods.notificationStatus(state.getResourceID()));
        NotifySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            DataBase.Methods.addNotificationToBase(state);
            DataBase.notifyStatus.put(state.getResourceID(), true);
        } else {
            DataBase.Methods.deleteNotificationFromBase(state);
            DataBase.notifyStatus.put(state.getResourceID(), false);
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
