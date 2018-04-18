package com.pum2018.pillreminder_v2018_04_03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;

import static com.pum2018.pillreminder_v2018_04_03.R.layout.activity_schedule_new_update;


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
            //I want to get info about medicines:
            Integer curr_med_id = curr_takingsPlan.getMedicine_id();
            Medicine curr_medicine = dbm.dbGetMedicine(curr_med_id);
            dbm.close();

            //Set value on Activity components:
            //Set Days:
            //ustawienie zmiennych lokalnych:
            local_Sunday = true;
            local_Monday = true;
            local_Tuesday = true;
            local_Wednesday = true;
            local_Thursday = false;
            local_Friday = false;
            local_Saturday = false;

            //Ustawienie wartosci w check-box-ach:
            cb_Sun.setChecked(local_Sunday);
            cb_Mon.setChecked(local_Monday);
            cb_Tue.setChecked(local_Tuesday);
            cb_Wed.setChecked(local_Wednesday);
            cb_Thu.setChecked(local_Thursday);
            cb_Fri.setChecked(local_Friday);
            cb_Sat.setChecked(local_Saturday);

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

    public void save(View view){

    }

    public void showToast(String text) {

        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }


}