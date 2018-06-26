package com.ofoegbuvgmail.accidentreport.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.ofoegbuvgmail.accidentreport.R;
import com.ofoegbuvgmail.accidentreport.database.AlertEntry;
import com.ofoegbuvgmail.accidentreport.database.AppDatabase;
import com.ofoegbuvgmail.accidentreport.database.AppExecutors;

import java.util.Calendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendAlertDialogFragment extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private MaterialSpinner alertTypeSpinner;
    private TextInputEditText alertDescription;
    private TextInputEditText alertLocation;
    private Button pictureButton;
    private Button sendButton;
    private String spinnerSelection;
    private String timeOfAlert;
    private String dateOfAlert;

    // Member variable for the Database
    private AppDatabase mDb;
    private String[] alertItems = {"Fatal Accident", "Medical Emergency", "Fire Incident", "Natural Disaster", "Plane Crash"};

    public SendAlertDialogFragment() {
        // Required empty public constructor
    }


    public static SendAlertDialogFragment newInstance() {
        SendAlertDialogFragment frag = new SendAlertDialogFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_send_alert_dialog, container, false);

        mDb = AppDatabase.getAlertInstance(getActivity());
        //Set the Items to the Nature of Injury spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, alertItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alertTypeSpinner = rootView.findViewById(R.id.alert_type_spinner);
        alertTypeSpinner.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alertTypeSpinner.setOnItemSelectedListener(this);
        alertDescription = view.findViewById(R.id.alert_description_text_input);
        alertLocation = view.findViewById(R.id.alert_route_text_input);
        pictureButton = view.findViewById(R.id.alertImagePicker);
        pictureButton.setOnClickListener(this);
        sendButton = view.findViewById(R.id.send_alert);
        sendButton.setOnClickListener(this);

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        timeOfAlert = hour + ":" + minute + c.get(Calendar.AM_PM);
        dateOfAlert = year + "/" + month + "/" + day;

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }

    public void saveInAlertDb() {
        String description = alertDescription.getText().toString();
        String location = alertLocation.getText().toString();
        String imageUrl = "www.jumia.com";

        AlertEntry alertEntry = new AlertEntry(spinnerSelection, description, location, imageUrl, timeOfAlert, dateOfAlert);
        AppExecutors.getInstance().diskIO().execute(() -> {
            //insert new Alert
            mDb.alertDao().insertAlert(alertEntry);
            Log.d("SendAlertDialogFragment", "Alert saved in Room Database");
            AppExecutors.getInstance().mainThread().execute(() ->
                    Toast.makeText(getActivity(), "Alert sent Successfully", Toast.LENGTH_LONG).show());
            getDialog().dismiss();
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_alert:
                saveInAlertDb();
                break;
            case R.id.alertImagePicker:
                ImagePicker.create(getActivity())
                        .start();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            List<Image> images = ImagePicker.getImages(data);
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0)
        spinnerSelection = alertItems[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
