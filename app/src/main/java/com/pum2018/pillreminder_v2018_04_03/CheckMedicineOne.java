package com.pum2018.pillreminder_v2018_04_03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;

/**
 * Created by Wlodek on 2018-04-08.
 */

public class CheckMedicineOne extends AppCompatActivity {

    //variables to access activity objects
    EditText editMedicine, editQuantity;
    RadioGroup rg1, rg2;
    int chkId1 = 0, chkId2 = 0;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_medicine_one);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // 1. get passed intent
        Intent intent = getIntent();
        // 2. get "Record_ID" value from intent
        Integer record_id = intent.getIntExtra("Record_ID",0);
        // 3. show on Toast
        Toast.makeText(this,"Update record:" + record_id.toString(),Toast.LENGTH_SHORT).show();

        //variables:
        editMedicine = (EditText) findViewById(R.id.medicine_edit_text_view);

        //Załadowanie danych do pol:
        editMedicine.setText("Nazwa Pastylki"); //To zadziałało!
        /*
        * na podstawie numeru rekordu - odczytujemy z tabeli Medicines informacje o pojedynczej Medicine
         * Potem ładujemy te dane do formatki,
         * Potem jest edycja użytkownika
         * Potem jest odczytanie z formatki tego co zostało tam jest
         * Potem zapisujemy wszystko do bazy pod własciwym ID
         * Koniec Update-u!*/


    }
}
