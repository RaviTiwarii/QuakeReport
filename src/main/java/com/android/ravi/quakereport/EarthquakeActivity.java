package com.android.ravi.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private TextView emptyStateTextView;
    private EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        emptyStateTextView = findViewById(R.id.empty_view);

        // Find a reference to the {@link ListView} in the layout.
        final ListView earthquakeListView = findViewById(R.id.list_view);
        earthquakeListView.setEmptyView(emptyStateTextView);

        adapter = new EarthquakeAdapter(this, new ArrayList<>());
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setOnItemClickListener((parent, view, position, id) -> {
            Earthquake currentEarthquake = adapter.getItem(position);
            Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
            startActivity(websiteIntent);
        });

        final LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader,
                               @Nullable List<Earthquake> earthquakes) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        emptyStateTextView.setText(R.string.no_earthquakes);

        adapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) adapter.addAll(earthquakes);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
        adapter.clear();
    }
}
