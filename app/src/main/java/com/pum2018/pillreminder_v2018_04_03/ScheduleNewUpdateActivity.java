package com.pum2018.pillreminder_v2018_04_03;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;


import java.util.Calendar;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;

import java.util.Calendar;

import static com.pum2018.pillreminder_v2018_04_03.R.layout.activity_schedule_new_update;
import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.padLZero;


/**
 * Created by Wlodek on 2018-04-16.
 */

public class ScheduleNewUpdateActivity extends AppCompatActivity {

    Boolean local_Sunday = false;
    Boolean local_Monday = false;
    Boolean local_Tuesday = false;
    Boolean local_Wednesday = false;
    Boolean local_Thursday = false;
    Boolean local_Friday = false;
    Boolean local_Saturday = false;

    // _id of record to update (if Update);
    Integer iRec_ID_for_Update;

    //variables to access activity objects:
    TextView title;
    //SetDay:
    CheckBox cb_Sun, cb_Mon,cb_Tue,cb_Wed,cb_Thu,cb_Fri,cb_Sat;

    //Do obsługi zegarka (ustawianie czasu):
    TextView tvClock;
    Calendar currentTime;
    int hour, mhour, minute;
    String format;

    //Do obsługi wyboru Medicine:
    TextView tvMedicine;
    Button btn_SelectMedicine;

    //EditText editMedicine, editQuantity;
    //RadioGroup rg1, rg2;
    //int chkId1 = 0, chkId2 = 0;
    //Spinner spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_schedule_new_update);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //Read number of record from Extra :
        // 1. get passed intent
        Intent intent = getIntent();
        // 2. get "Record_ID" value from intent
        Integer record_id = intent.getIntExtra("Record_ID",0);
        //Remember for Save option:
        iRec_ID_for_Update = record_id;
        // 3. show on Toast
        Toast.makeText(this,"Update record:" + record_id.toString(),Toast.LENGTH_SHORT).show();

        //Jedna formatka obsługuje operację Update oraz Add, dlatego dlasze kroki zależne są od tego czy
        //jest to Update czy Add

        // variables to access activity objects:
        title = findViewById(R.id.textView5);
        cb_Sun = (CheckBox)findViewById(R.id.checkBox1);
        cb_Mon = (CheckBox)findViewById(R.id.checkBox2);
        cb_Tue = (CheckBox)findViewById(R.id.checkBox3);
        cb_Wed = (CheckBox)findViewById(R.id.checkBox4);
        cb_Thu = (CheckBox)findViewById(R.id.checkBox5);
        cb_Fri = (CheckBox)findViewById(R.id.checkBox6);
        cb_Sat = (CheckBox)findViewById(R.id.checkBox7);;

        //variable to access to Medicine Name and Form:
        TextView tv_current_MedicineName = (TextView)findViewById(R.id.textView_Medicine);
        TextView tv_current_MedicineForm = (TextView)findViewById(R.id.textView_FormMedicine);


        //Do obsługi wyskakującego zegarka do ustawiania czasu:
        tvClock = (TextView) findViewById(R.id.textViewClock);
        currentTime = Calendar.getInstance();
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);
        //mhour = selectedTimeFormat(hour);
        tvClock.setText(hour + " : " + minute); //+ " " + format);
        tvClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleNewUpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //hourOfDay = selectedTimeFormat(hourOfDay);
                        tvClock.setText(hourOfDay + " : " + minute + " " + format);
                    }
                }, hour , minute, true);
                timePickerDialog.show();
            }
        });

/*        //Do obsługi wyboru Medicine (Buttonem):
        //tvMedicine = (TextView) findViewById(R.id.textView_Medicine);
        btn_SelectMedicine = (Button) findViewById(R.id.buttonSelectMedicine);
        //tvMedicine.setOnClickListener(new View.OnClickListener() {
        btn_SelectMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Wywołamy teraz nową formatkę - przekażemy do niej numer rekordu w tabeli Medicine.
                //Rekord ten bedzie w wywoływanej formatce wykorzystany do podswietlenia
                Intent selectMedicineIntent = new Intent(this,SelectMedicine.class);
                // Put key/value data
                checkMedicineIntent.putExtra("Record_ID", id_Int);
                startActivity(checkMedicineIntent);
            }
        });*/



        //Set Title of Activity:
        if (iRec_ID_for_Update>0){
            //Update schedule:
            title.setText("UPDATE SCHEDULE");
        }else{
            //New Schedule:
            title.setText("NEW SCHEDULE");
        }


        //Set start values:
        if (iRec_ID_for_Update>0){
            //Update schedule:

            //na podstawie numeru rekordu - odczytujemy z tabeli TAKINGS_PLAN_TABLE informacje o pojedynczej schedule:
            DataBaseManager dbm = new DataBaseManager(this);
            //Get TakingsPlan object to temporary object in memory:
            TakingsPlan curr_takingsPlan = dbm.dbGetTakingsPlan(iRec_ID_for_Update);
            //I want to get info about medicines too:
            Integer curr_med_id = curr_takingsPlan.getMedicine_id();
            Medicine curr_medicine = dbm.dbGetMedicine(curr_med_id);
            dbm.close();

            //Set value on Activity components:
            //Time:
            tvClock.setText(padLZero(curr_takingsPlan.getHour().toString())+":"+padLZero(curr_takingsPlan.getMinute().toString()));


            //Medicine:
            tv_current_MedicineName.setText(curr_medicine.getName());
            tv_current_MedicineForm.setText(curr_medicine.getFormMedicine());

            //Set Days:
            //Ustawienie wartosci w check-box-ach ( + zamiana Integer na Boolean):
            cb_Sun.setChecked(curr_takingsPlan.getDay_sunday() != 0);
            cb_Mon.setChecked(curr_takingsPlan.getDay_monday() != 0);
            cb_Tue.setChecked(curr_takingsPlan.getDay_tuesday() != 0);
            cb_Wed.setChecked(curr_takingsPlan.getDay_wednesday() != 0);
            cb_Thu.setChecked(curr_takingsPlan.getDay_thursday() != 0);
            cb_Fri.setChecked(curr_takingsPlan.getDay_friday() != 0);
            cb_Sat.setChecked(curr_takingsPlan.getDay_saturday() != 0);

        }else{
            //New Schedule:

        }

    }



    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked) {
                    local_Sunday = true;
                    showToast("Sunday set");
                } else  {
                    local_Sunday = false;
                    showToast("Sunday clear");
                    break;}
            case R.id.checkBox2:
                if (checked) {
                    local_Monday = true;
                    showToast("Monday set");
                } else  {
                    local_Monday = false;
                    showToast("Monday clear");
                    break;}
            case R.id.checkBox3:
                if (checked) {
                    local_Monday = true;
                    showToast("Tuesday set");
                } else  {
                    local_Monday = false;
                    showToast("Tuesday clear");
                    break;}
            case R.id.checkBox4:
                if (checked) {
                    local_Monday = true;
                    showToast("Wednesday set");
                } else  {
                    local_Monday = false;
                    showToast("Wednesday clear");
                    break;}
            case R.id.checkBox5:
                if (checked) {
                    local_Monday = true;
                    showToast("Thursday set");
                } else  {
                    local_Monday = false;
                    showToast("Thursday clear");
                    break;}
            case R.id.checkBox6:
                if (checked) {
                    local_Monday = true;
                    showToast("Friday set");
                } else  {
                    local_Monday = false;
                    showToast("Friday clear");
                    break;}
            case R.id.checkBox7:
                if (checked) {
                    local_Monday = true;
                    showToast("Saturday set");
                } else  {
                    local_Monday = false;
                    showToast("Saturday clear");
                    break;}
        }


    }


    public void selectMedicineFromOtherActivity(View view){
        Intent selectMedicineIntent = new Intent(ScheduleNewUpdateActivity.this,SelectMedicine.class);
        startActivity(selectMedicineIntent);
    }


    public void save(View view){

    }

    public void showToast(String text) {

        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }


}