package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Marc on 30.09.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    private static final String LOCATION_SEPARATOR = " of ";

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


        // find the TextView that display's the magnitude of the current Earthquake.
        TextView magnitudeView = convertView.findViewById(R.id.magnitude);

        // Format the magnitude, so that it only shows one decimal place.
        String formattedMagnitude = formatMagnitude(current.getMagnitude());
        // Set its magnitude.
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(current.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);




        // Split the original location into an offset and a primary location.
        String [] formattedLocation = splitLocation(current.getLocation());

        TextView primaryLocation = convertView.findViewById(R.id.primary_location);
        primaryLocation.setText(formattedLocation[1]);

        TextView locationOffset = convertView.findViewById(R.id.location_offset);
        locationOffset.setText(formattedLocation[0]);



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

    private String[] splitLocation(String location) {
        if (location.contains(LOCATION_SEPARATOR)) {
            String[] parts = location.split(LOCATION_SEPARATOR);
            String locationOffset = parts[0] + LOCATION_SEPARATOR;
            String primaryLocation = parts[1];
            return new String [] {locationOffset.trim(), primaryLocation};
        }
        return new String[] {getContext().getString(R.string.near_the), location};
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private int getMagnitudeColor (double magnitude) {
        int magnitudeFloor = (int) magnitude;
        int magnitudeColorResourceId;

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
