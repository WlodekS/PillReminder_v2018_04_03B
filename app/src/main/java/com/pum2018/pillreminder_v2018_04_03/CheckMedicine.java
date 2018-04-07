package com.pum2018.pillreminder_v2018_04_03;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;

import java.util.List;

/**
 * Created by Wlodek on 2018-04-04.
 */

public class CheckMedicine extends AppCompatActivity {

    private DataBaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_medicine);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //initialize DataBase Manager for Activity:
        dbm = new DataBaseManager(this);






    }
}
