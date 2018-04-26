package com.pum2018.pillreminder_v2018_04_03;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
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
import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.getHoursFromStringTime;
import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.getMinutesFromStringTime;
import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.padLZero;


/**
 * Created by PillReminderGroup on 2018-04-16.
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
    // _id of Medicine (Fereign Key):
    Integer iRec_Medicine_ID;

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

    //Te zmienne deklarujemy jako zmienne klasy
    //- bo niektóre z nich będa modyfikowane w onActivityResult
    TextView tv_current_Medicine_ID;
    TextView tv_current_MedicineName;
    TextView tv_current_MedicineForm;
    TextView tv_current_MedicineQuantity;
    EditText et_current_MedicineQuantity;
    TextView tv_current_MedicineDoseOption;



    TakingsPlan curr_takingsPlan;


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

        //variable to access to Medicine ID, Name and Form, quantity i dose option:
        tv_current_Medicine_ID = (TextView) findViewById(R.id.textView_Medicine_ID);
        tv_current_MedicineName = (TextView)findViewById(R.id.textView_Medicine);
        tv_current_MedicineForm = (TextView)findViewById(R.id.textView_FormMedicine);
        tv_current_MedicineQuantity = (TextView)findViewById(R.id.textView_SetAlarm_Quantity);
        et_current_MedicineQuantity = (EditText) findViewById(R.id.editText_SetAlarm_Quantity);
        tv_current_MedicineDoseOption = (TextView)findViewById(R.id.textView_SetAlarmDoseOption);

        //Do obsługi wyskakującego zegarka do ustawiania czasu:
        tvClock = (TextView) findViewById(R.id.textViewClock);
        currentTime = Calendar.getInstance();
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);
        //mhour = selectedTimeFormat(hour);

        //Ustawienie początkowe zegara:
        tvClock.setText(hour + " : " + minute); //+ " " + format);

        tvClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ScheduleNewUpdateActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //hourOfDay = selectedTimeFormat(hourOfDay);
                        tvClock.setText(hourOfDay + " : " + minute + " " + format);
                    }
                }, hour , minute, true);*/
                //Odczytanie godziny i minuty z ustawień alarmu na ekranie:
                //int intHH = curr_takingsPlan.getHour();
                //int intMM = curr_takingsPlan.getMinute();
                int intHH = getHoursFromStringTime((String) tvClock.getText());
                int intMM = getMinutesFromStringTime((String) tvClock.getText());

                 TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ScheduleNewUpdateActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //hourOfDay = selectedTimeFormat(hourOfDay);
                        //tvClock.setText(hourOfDay + " : " + minute + " " + format);
                        //Odczyt czasu:
                        Integer intGodzina = view.getHour();
                        Integer intMinuta = view.getMinute();
                        String strGodzina = padLZero(String.valueOf(intGodzina));
                        String strMinuta = padLZero(String.valueOf(intMinuta));
                        tvClock.setText(strGodzina+":"+strMinuta);
                    }
                }, intHH , intMM, true);

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
            curr_takingsPlan = dbm.dbGetTakingsPlan(iRec_ID_for_Update);
            //I want to get info about medicines too:
            Integer curr_med_id = curr_takingsPlan.getMedicine_id();
            Medicine curr_medicine = dbm.dbGetMedicine(curr_med_id);
            //Dodatkowo zapisuję Medicine_ID do zmiennej klasy, żeby wykorzystać przy wywołaniu nowego Activity:
            iRec_Medicine_ID = curr_med_id;
            dbm.close();

            //Set value on Activity components:
            //Time:
            tvClock.setText(padLZero(curr_takingsPlan.getHour().toString())+":"+padLZero(curr_takingsPlan.getMinute().toString()));
            //Id dla Medicine:
            tv_current_Medicine_ID.setText(curr_med_id.toString());
            //Medicine:
            tv_current_MedicineName.setText(curr_medicine.getName());
            tv_current_MedicineForm.setText(curr_medicine.getFormMedicine());
            //Dawkowanie:
            tv_current_MedicineQuantity.setText(curr_takingsPlan.getDose().toString());
            et_current_MedicineQuantity.setText(curr_takingsPlan.getDose().toString());
            tv_current_MedicineDoseOption.setText(curr_medicine.getDose_option());

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

            //Na początek przyjmiemy że domyślnie ustalonym Medicine jest Medicine o ID =1
            DataBaseManager dbm = new DataBaseManager(this);
            //Get TakingsPlan object to temporary object in memory:
            //curr_takingsPlan = dbm.dbGetTakingsPlan(iRec_ID_for_Update);
            //I want to get info about medicines too:
            //Integer curr_med_id = curr_takingsPlan.getMedicine_id();
            Medicine curr_medicine = dbm.dbGetMedicine(1);  //Rozwiązanie szkolne. W zyciu - może to być rekord skasowany.
            //Dodatkowo zapisuję Medicine_ID do zmiennej klasy, żeby wykorzystać przy wywołaniu nowego Activity:
            iRec_Medicine_ID = 1;
            dbm.close();

            //Set value on Activity components:
            //Time:
            tvClock.setText(padLZero("0")+":"+padLZero("0"));
            //Id dla Medicine:
            tv_current_Medicine_ID.setText("1");
            //Medicine:
            tv_current_MedicineName.setText(curr_medicine.getName());
            tv_current_MedicineForm.setText(curr_medicine.getFormMedicine());
            //Dawkowanie:
            tv_current_MedicineQuantity.setText("1");
            et_current_MedicineQuantity.setText("1");
            tv_current_MedicineDoseOption.setText(curr_medicine.getDose_option());

            //Set Days:
            //Ustawienie wartosci w check-box-ach ( + zamiana Integer na Boolean):
            cb_Sun.setChecked(false);
            cb_Mon.setChecked(false);
            cb_Tue.setChecked(false);
            cb_Wed.setChecked(false);
            cb_Thu.setChecked(false);
            cb_Fri.setChecked(false);
            cb_Sat.setChecked(false);
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
                } else {
                    local_Sunday = false;
                    showToast("Sunday clear");
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    local_Monday = true;
                    showToast("Monday set");
                } else  {
                    local_Monday = false;
                    showToast("Monday clear");
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    local_Monday = true;
                    showToast("Tuesday set");
                } else  {
                    local_Monday = false;
                    showToast("Tuesday clear");
                }
                break;
            case R.id.checkBox4:
                if (checked) {
                    local_Monday = true;
                    showToast("Wednesday set");
                } else {
                    local_Monday = false;
                    showToast("Wednesday clear");
                }
                break;
            case R.id.checkBox5:
                if (checked) {
                    local_Monday = true;
                    showToast("Thursday set");
                } else {
                    local_Monday = false;
                    showToast("Thursday clear");
                }
                break;
            case R.id.checkBox6:
                if (checked) {
                    local_Monday = true;
                    showToast("Friday set");
                } else {
                    local_Monday = false;
                    showToast("Friday clear");
                }
                break;
            case R.id.checkBox7:
                if (checked) {
                    local_Monday = true;
                    showToast("Saturday set");
                } else {
                    local_Monday = false;
                    showToast("Saturday clear");
                }
                break;
        }


    }


    public void selectMedicineFromOtherActivity(View view){

        //Do wywoływanej tu Activity wysyłam informacje o numerach edytowanego rekordu i Medicine_ID:
        //create a Bundle object
        Bundle extras = new Bundle();
        //Adding key value pairs to this bundle
        //there are quite a lot data types you can store in a bundle
        extras.putInt("ALARM_ID", iRec_ID_for_Update);
        extras.putInt("MEDICINE_ID", iRec_Medicine_ID);

        //create and initialize an intent
        Intent selectMedicineIntent = new Intent(ScheduleNewUpdateActivity.this,SelectMedicine.class);
        //attach the bundle to the Intent object
        selectMedicineIntent.putExtras(extras);

        //finally start the activity
        //startActivity(selectMedicineIntent);
        startActivityForResult(selectMedicineIntent, 2);// Activity is started with requestCode 2

    }

    // Call Back method  to get the Message form other Activity    override the method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);


        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            //Wersja z pojedynczą informacją
            // fetch the message String
            //String message=data.getStringExtra("MESSAGE");
            // Set the message string in textView
            //tv_current_MedicineName.setText(message);

            //Odczytamy wartościprzekazane w Extras. Ustawimy tez dwie zmienne klasy, tak, żeby
            //get the attached bundle from the data (intent)
            if (data!=null) {
                Bundle extras = data.getExtras();

                //Extracting and saving the stored data from the bundle
                boolean selectedTheSame = extras.getBoolean("SELECTED_TE_SAME");

                if (selectedTheSame == true) {
                    //Nie zmieniamy nic.
                    //tv_current_MedicineName.setText("To samo");
                } else {
                    //Zmieniamy ustawienia w Activity, ponieważ Medicine zostało wybrane już inne:
                    // 1. textView_Medicine_ID        - Umieszczone w niewidocznym textView Medicine_ID
                    // 2. textView_Medicine           - Nazwa Medicine
                    // 3. textView_FormMedicine       - Medicine Form (tablet, kapsule itp.)
                    // 4. textView_SetAlarm_Quantity  - Zażywana ilość - dla nowej Medicine - domyślnie 1
                    // 5. textView_SetAlarmDoseOption - sztuki, ml ...
                    //Ad.1:
                    Integer currMedID = extras.getInt("KEY_MEDICINE_ID");
                    tv_current_Medicine_ID.setText(currMedID.toString());
                    //Ad.2:
                    tv_current_MedicineName.setText(extras.getString("KEY_MEDICINE_NAME"));
                    //Ad.3:
                    tv_current_MedicineForm.setText(extras.getString("KEY_MEDICINE_FORM"));
                    //Ad.4:
                    tv_current_MedicineQuantity.setText("1");
                    et_current_MedicineQuantity.setText("1");
                    //Ad.5:
                    tv_current_MedicineDoseOption.setText(extras.getString("KEY_MEDICINE_DOSE_OPTION"));
                }
            }
            else
            {
                //data==null => nic nie wybrano na liście wybory medicine
                //Nie robimy nic.

            }
        }

    }



    public void save(View view){

        //Save data from Activity to database:
        //Saving to Database:
        DataBaseManager dbm = new DataBaseManager(this);
        SQLiteDatabase db = dbm.getReadableDatabase();

        //parametry konieczne do zapisu do bazy:
        //Hour, Minute:
        String modi_Time = (String) tvClock.getText();
        int intHH = getHoursFromStringTime(modi_Time);
        int intMM = getMinutesFromStringTime(modi_Time);
        int intModiDose = Integer.parseInt(et_current_MedicineQuantity.getText().toString());

        //Creating new TakinPlan object:
        TakingsPlan modifyingTakingPlan = new TakingsPlan();
        //The First - set 0r not appropriate ID:
        //Jeśli iRec_ID_for_Update = 0 to znaczy że jest to NOWE schedule:
        if (iRec_ID_for_Update!=0) {
            modifyingTakingPlan.set_id(iRec_ID_for_Update);
        } else {
            //Nowe schedule - nie ustawiamy ID
            // I do NOTHING
        }

        //Then - set other params:
        modifyingTakingPlan.setHour(intHH);
        modifyingTakingPlan.setMinute(intMM);
        modifyingTakingPlan.setMedicine_id(Integer.parseInt((String) tv_current_Medicine_ID.getText()));
        modifyingTakingPlan.setDose(intModiDose);
        modifyingTakingPlan.setDay_sunday(cb_Sun.isChecked() ? 1:0);
        modifyingTakingPlan.setDay_monday(cb_Mon.isChecked() ? 1:0);
        modifyingTakingPlan.setDay_tuesday(cb_Tue.isChecked() ? 1:0);
        modifyingTakingPlan.setDay_wednesday(cb_Wed.isChecked() ? 1:0);    //0,1
        modifyingTakingPlan.setDay_thursday(cb_Thu.isChecked() ? 1:0);
        modifyingTakingPlan.setDay_friday(cb_Fri.isChecked() ? 1:0);
        modifyingTakingPlan.setDay_saturday(cb_Sat.isChecked() ? 1:0);

        //Zapis do bazy:
        if (iRec_ID_for_Update!=0) {
            //Modyfikacja rekordu:
            dbm.dbUpdateTakingsPlan(modifyingTakingPlan);
        } else {
            //Dodanie rekordu:
            dbm.dbCreateTakingPlan(modifyingTakingPlan);
        }

        dbm.close();

        //finish Activity:
        finish();

    }

    public void showToast(String text) {

        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }


}