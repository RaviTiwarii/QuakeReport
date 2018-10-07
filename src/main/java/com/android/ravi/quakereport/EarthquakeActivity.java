package com.android.ravi.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        // Find a reference to the {@link ListView} in the layout.
        ListView earthquakeListView = findViewById(R.id.list_view);

        // Create a new adapter that takes the list of earthquakes as input.
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, fakeEarthquakes());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface.
        earthquakeListView.setAdapter(adapter);
    }


    private List<Earthquake> fakeEarthquakes() {
        List<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("7.2", "San Fransico", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("6.1", "London", "July 20, 2015"));
        earthquakes.add(new Earthquake("3.9", "Tokyo", "Nov 10, 2014"));
        earthquakes.add(new Earthquake("5.4", "Mexico City", "May 3, 2014"));
        earthquakes.add(new Earthquake("2.8", "Moscow", "Jan 31, 2013"));
        earthquakes.add(new Earthquake("4.9", "Rio de Janerio", "Aug 19, 2012"));
        earthquakes.add(new Earthquake("1.6", "Paris", "Oct 30, 2011"));

        return earthquakes;
    }
}
