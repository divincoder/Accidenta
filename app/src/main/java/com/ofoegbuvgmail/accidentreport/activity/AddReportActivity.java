package com.ofoegbuvgmail.accidentreport.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.ofoegbuvgmail.accidentreport.R;
import com.ofoegbuvgmail.accidentreport.database.AccidentEntry;
import com.ofoegbuvgmail.accidentreport.database.AppDatabase;
import com.ofoegbuvgmail.accidentreport.database.AppExecutors;

import java.util.Calendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class AddReportActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = "AddReportActivity";
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateSelector;
    private Button timeSelector;
    private Button imagePicker;
    private MaterialSpinner natureOfInjurySpinner;
    private MaterialSpinner damageToVehicleSpinner;
    private TextInputEditText routeAddress;
    private TextInputEditText causeOfAccident;
    private TextInputEditText description;
    private TextInputEditText vehiclePlateNo;
    private TextInputEditText vehicleModel;
    private TextInputEditText reporterName;
    private TextInputEditText phoneNumber;
    private Button sendReport;
    private String timeOfAccident;
    private String dateOfAccident;
    private String natureOfInjury;
    private String damageToVehicle;

    private String[] ITEMS = {"Fatal", "Minor", "Serious", "Unknown"};
    private String[] vehicleItems = {"Severe", "Minor", "Moderate", "Unknown"};

    // Member variable for the Database
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.report_accident));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        mDb = AppDatabase.getInstance(this);

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        timePickerDialog = new TimePickerDialog(this, this, hour, minute, DateFormat.is24HourFormat(this));

        dateSelector.setOnClickListener(v -> datePickerDialog.show());
        timeSelector.setOnClickListener(v -> timePickerDialog.show());

        //Set the Items to the Nature of Injury spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        natureOfInjurySpinner = findViewById(R.id.injury_nature_spinner);
        natureOfInjurySpinner.setAdapter(adapter);
        natureOfInjurySpinner.setOnItemSelectedListener(this);

        //Set the Items to the Vehicle Damage spinner
        ArrayAdapter<String> vehicleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vehicleItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        damageToVehicleSpinner = findViewById(R.id.vehicle_damage_spinner);
        damageToVehicleSpinner.setAdapter(vehicleAdapter);
        damageToVehicleSpinner.setOnItemSelectedListener(this);

    }

    private void initViews() {

        dateSelector = findViewById(R.id.datePicker);
        timeSelector = findViewById(R.id.timePicker);
        imagePicker = findViewById(R.id.imagePicker);
        imagePicker.setOnClickListener(this);
        routeAddress = findViewById(R.id.route_text_input);
        causeOfAccident = findViewById(R.id.causeOfAccident_text_input);
        description = findViewById(R.id.description_text_input);
        vehiclePlateNo = findViewById(R.id.vehicle_plate_input);
        vehicleModel = findViewById(R.id.vehicle_model_input);
        reporterName = findViewById(R.id.reporterName_text_input);
        phoneNumber = findViewById(R.id.phoneNo_text_input);
        sendReport = findViewById(R.id.send_report);
        sendReport.setOnClickListener(this);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "/" + month + "/" + dayOfMonth;
        dateSelector.setText(date);
        dateOfAccident = date;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = hourOfDay + ":" + minute;
        timeSelector.setText(time);
        timeOfAccident = time;
    }

    private void saveDataInDb() {
        String route = routeAddress.getText().toString();
        String accidentCause = causeOfAccident.getText().toString();
        String accidentDescription = description.getText().toString();
        String vehiclePlate = vehiclePlateNo.getText().toString();
        String model = vehicleModel.getText().toString();
        String rName = reporterName.getText().toString();
        String phoneNo = phoneNumber.getText().toString();

        String imageUrl = "https://jumia.com";

        AccidentEntry accidentEntry = new AccidentEntry(route, accidentCause, accidentDescription, timeOfAccident,
                dateOfAccident, imageUrl, natureOfInjury, vehiclePlate, model, damageToVehicle, rName, phoneNo);

        //Save to DB
        AppExecutors.getInstance().diskIO().execute(() -> {
            // insert new task
            mDb.accidentDao().insertTask(accidentEntry);
            Log.d(TAG, "Task saved in Room Database");

            AddReportActivity.this.finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.injury_nature_spinner:
                natureOfInjury = ITEMS[position];
                break;
            case R.id.vehicle_damage_spinner:
                damageToVehicle = vehicleItems[position];
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            List<Image> images = ImagePicker.getImages(data);
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send_report:
                saveDataInDb();
                break;
            case R.id.imagePicker:
                ImagePicker.create(this)
                        .start();
                break;
        }

    }
}
