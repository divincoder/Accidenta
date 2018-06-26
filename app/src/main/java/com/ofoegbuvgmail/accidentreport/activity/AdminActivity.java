package com.ofoegbuvgmail.accidentreport.activity;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ofoegbuvgmail.accidentreport.AccidentAdapter;
import com.ofoegbuvgmail.accidentreport.R;
import com.ofoegbuvgmail.accidentreport.database.AccidentEntry;
import com.ofoegbuvgmail.accidentreport.database.AppDatabase;

import java.util.List;

public class AdminActivity extends AppCompatActivity implements AccidentAdapter.ItemClickListener {

    private final String TAG = AdminActivity.class.getSimpleName();
    private TextView emptyStateText;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_admin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mRecyclerView = findViewById(R.id.admin_recycler);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new AccidentAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        mDb = AppDatabase.getInstance(this);

//        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
//        mRecyclerView.addItemDecoration(decoration);
        retrieveDataEntries();
    }

    private void retrieveDataEntries() {
        Log.d(TAG, "Actively retrieving the accidents from the DataBase");
        LiveData<List<AccidentEntry>> tasks = mDb.accidentDao().loadAllAccidents();
        tasks.observe(this, dataEntries -> {
            Log.d(TAG, "Receiving database update from LiveData");
            mAdapter.setDataEntries(dataEntries);
        });
    }

    @Override
    public void onItemClickListener(int itemId) {

    }
}
