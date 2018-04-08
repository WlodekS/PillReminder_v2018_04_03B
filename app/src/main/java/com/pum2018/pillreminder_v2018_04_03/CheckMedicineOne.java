package com.pum2018.pillreminder_v2018_04_03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;

/**
 * Created by Wlodek on 2018-04-08.
 */

public class CheckMedicineOne extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_medicine);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Wywołanie edycji pojedynczej Medicine:
        Intent checkMedicineToStoreIntent = new Intent(CheckMedicineOne.this,CheckMedicineOne.class);
        startActivity(checkMedicineToStoreIntent);

    }
}
