package com.ofoegbuvgmail.accidentreport.fragments;


import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ofoegbuvgmail.accidentreport.AccidentAdapter;
import com.ofoegbuvgmail.accidentreport.AlertAdapter;
import com.ofoegbuvgmail.accidentreport.R;
import com.ofoegbuvgmail.accidentreport.database.AccidentEntry;
import com.ofoegbuvgmail.accidentreport.database.AlertEntry;
import com.ofoegbuvgmail.accidentreport.database.AppDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertsFragment extends Fragment implements AlertAdapter.ItemClickListener{

    private AlertAdapter mAdapter;
    private AppDatabase mDb;
    private final String TAG = AlertsFragment.class.getSimpleName();

    public AlertsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Bundle args) {
        Fragment frag = new AlertsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_alerts, container, false);

        FloatingActionButton fab = rootView.findViewById(R.id.alert_fab);
        fab.setOnClickListener(v -> {
            showAlertDialog();
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.alert_recyclerView);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new AlertAdapter(getActivity(), this);

        recyclerView.setAdapter(mAdapter);

        mDb = AppDatabase.getAlertInstance(getActivity());

        retrieveDataEntries();
    }

    private void retrieveDataEntries() {
        Log.d(TAG, "Actively retrieving all alerts from the DataBase");
        LiveData<List<AlertEntry>> tasks = mDb.alertDao().loadAllAlerts();
        tasks.observe(this, dataEntries -> {
            Log.d(TAG, "Receiving database update from LiveData");
            mAdapter.setDataEntries(dataEntries);
        });
    }

    private void showAlertDialog() {

        FragmentManager fm = getFragmentManager();
        SendAlertDialogFragment sendAlertDialogFragment = SendAlertDialogFragment.newInstance();
        sendAlertDialogFragment.show(fm, "fragment_login");
    }

    @Override
    public void onItemClickListener(int itemId) {

    }
}
