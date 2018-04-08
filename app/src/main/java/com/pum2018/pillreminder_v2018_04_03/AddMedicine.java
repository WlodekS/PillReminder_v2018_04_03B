package com.pum2018.pillreminder_v2018_04_03;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.FormMedicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;

public class AddMedicine extends AppCompatActivity {

    //variables to access activity objects
    RadioGroup rg1, rg2;
    EditText editQuantity, editMedicine;
    int chkId1 = 0, chkId2 = 0;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        rg1 = (RadioGroup) findViewById(R.id.radio_group_left);
        rg2 = (RadioGroup) findViewById(R.id.radio_group_right);
        editQuantity = (EditText) findViewById(R.id.edit_quantity);
        editMedicine = (EditText) findViewById(R.id.medicine_edit_text_view);
        rg1.clearCheck();
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);
        spinner = (Spinner) findViewById(R.id.spinner_dose_type);
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != 0) {
                rg2.setOnCheckedChangeListener(null);
                chkId1 = rg1.getCheckedRadioButtonId();
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(listener2);

            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != 0) {
                rg1.setOnCheckedChangeListener(null);
                chkId2 = rg2.getCheckedRadioButtonId();
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);
            }
        }
    };

    public void addMedicineToStore(View view){
        String quantity, medicineName;
        quantity = editQuantity.getText().toString();
        medicineName = editMedicine.getText().toString();
        if (chkId1 != 0 || chkId2 != 0 && (!(medicineName.matches(""))) && (!(quantity.matches("")))) {
            int realCheck = chkId1 == 0 ? chkId2 : chkId1;
            int checkedRg1, checkedRg2 = 0;
            Log.i("You pressed", ": " + realCheck);
            Toast.makeText(this,"\tMedicine Name : "+ editMedicine.getText().toString()
                    + "\n\tMedicine Type : " + realCheck
                    + "\n\tMedicine Quantity : "+editQuantity.getText().toString(),Toast.LENGTH_LONG).show();


            // Converting values and saving to DataBase:
            int iSpinner = spinner.getSelectedItemPosition();       //int value-position(0,1...)
            int iQuantity = Integer.parseInt(quantity);
            //Create param - new Medicine object:
            Medicine newMedicine = new Medicine();
            newMedicine.setName(medicineName);          //name
            //newMedicine.setFormMedicine(realCheck);     //formMedicine (radioButton value)
            newMedicine.setFormMedicine(Medicine.FormMedInt2Str(realCheck));
            //newMedicine.setDose_option(iSpinner);       //dose_option
            newMedicine.setDose_option(Medicine.DoseOptionInt2Str(iSpinner));

            newMedicine.setQuantity(iQuantity);         //quantity

            //Saving to Database:
            DataBaseManager dbm = new DataBaseManager(this);
            dbm.dbCreateMedicine(newMedicine);
            dbm.TEST_TypeTableMedicines();
            dbm.close();


            checkedRg1 = rg1.getCheckedRadioButtonId();
            checkedRg2 = rg2.getCheckedRadioButtonId();
            if (checkedRg1 != 0) {
                rg1.clearCheck();
            }
            ;
            if (checkedRg2 != 0) {
                rg2.clearCheck();
            }
            editQuantity.setText("");
            editMedicine.setText("");
            chkId1 = 0;
            chkId2 = 0;
        } else if (medicineName.matches("")) {
            Toast.makeText(this,"Please provide Medicine Name",Toast.LENGTH_SHORT).show();
        } else if (quantity.matches("")){
            Toast.makeText(this,"Please provide Quantity of Medicine",Toast.LENGTH_SHORT).show();
        } else if (chkId1 == 0 && chkId2 == 0){
            Toast.makeText(this,"Please select proper Type of Medicine",Toast.LENGTH_SHORT).show();
        }
    }

    public void saveMedicineToStore(View view) {

        Log.i("ADD Medicine","Saving medicines to store");
        Toast.makeText(this,"Saving medicine to store",Toast.LENGTH_LONG).show();

    }


}
