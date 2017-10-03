package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Marc on 30.09.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{


    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        // Don't need a layout resource id, since we inflate them manually in getView().
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // If the convertView is null (re: it wasn't previously used), then we have to inflate it.
        if (convertView == null) {
            // obtain the layout inflater from the given context.
            convertView = LayoutInflater.from(getContext())
                    // inflate a new LinearLayout and reference it with convertView.
                    .inflate(R.layout.earthquake_list_item, parent, false);
        }

        // Get the next Earthquake object that needs to be displayed as a list item
        Earthquake current = getItem(position);


        // find all the textViews within the convertView LinearLayout, and fill them
        // with the info of the current Earthquake object.

        TextView magnitude = convertView.findViewById(R.id.magnitude);
        magnitude.setText(current.getMagnitude() + "");

        TextView location = convertView.findViewById(R.id.location);
        location.setText(current.getLocation());

        // Create a date object from the Unix time stamp
        Date dateObject = new Date(current.getTimeInMilliseconds());

        // Find the linearLayout that holds time and date inside the list item.
        View timeContainer = convertView.findViewById(R.id.timecontainer);
        // Format the date and write it in the TextView
        TextView date = timeContainer.findViewById(R.id.date);
        date.setText(formatDate(dateObject));
        // Format the time and write it in the TextView
        TextView time = timeContainer.findViewById(R.id.time);
        time.setText(formatTime(dateObject));

        return convertView;
    }

    private String formatDate(Date dateObject) {
        // We use the SimpleDateFormat class to convert the Unix time, to a human readable date.
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        return dateFormatter.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        // We use the SimpleDateFormat class to convert the Unix time, to human readable time.
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        return timeFormatter.format(dateObject);
    }
}
