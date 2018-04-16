package com.pum2018.pillreminder_v2018_04_03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_new_update);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked) {
                    local_Monday = true;
                    showToast("Sunday set");
                } else  {
                    local_Monday = false;
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