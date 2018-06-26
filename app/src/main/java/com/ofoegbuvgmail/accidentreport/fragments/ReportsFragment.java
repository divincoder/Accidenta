package com.ofoegbuvgmail.accidentreport.fragments;


import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ofoegbuvgmail.accidentreport.AccidentAdapter;
import com.ofoegbuvgmail.accidentreport.R;
import com.ofoegbuvgmail.accidentreport.activity.AddReportActivity;
import com.ofoegbuvgmail.accidentreport.database.AccidentEntry;
import com.ofoegbuvgmail.accidentreport.database.AppDatabase;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportsFragment extends Fragment implements AccidentAdapter.ItemClickListener {

    private final String TAG = "ReportsFragment";
    private TextView emptyStateText;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAdapter;
    private AppDatabase mDb;


    public ReportsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Bundle args) {
        Fragment frag = new ReportsFragment();
        if (args == null) {
            args = new Bundle();
        }
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reports, container, false);
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddReportActivity.class);
            startActivity(intent);
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.report_recyclerView);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new AccidentAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        mDb = AppDatabase.getInstance(getActivity());

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
