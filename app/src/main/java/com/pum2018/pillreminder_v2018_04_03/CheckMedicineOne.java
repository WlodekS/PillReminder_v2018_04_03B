package com.pum2018.pillreminder_v2018_04_03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;

/**
 * Created by Wlodek on 2018-04-08.
 */

public class CheckMedicineOne extends AppCompatActivity {

    //variables to access activity objects
    EditText editMedicine, editQuantity;
    RadioGroup rg1, rg2;
    int chkId1 = 0, chkId2 = 0;
    Spinner spinner;

    // _id of record to update;
    Integer iRec_ID_for_Update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_medicine_one);
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


        // variables to access activity objects:
        editMedicine = (EditText) findViewById(R.id.medicine_edit_text_view);
        rg1 = (RadioGroup) findViewById(R.id.radio_group_left);
        rg2 = (RadioGroup) findViewById(R.id.radio_group_right);
        editQuantity = (EditText) findViewById(R.id.edit_quantity);
        spinner = (Spinner) findViewById(R.id.spinner_dose_type);


        //na podstawie numeru rekordu - odczytujemy z tabeli Medicines informacje o pojedynczej Medicine:
        DataBaseManager dbm = new DataBaseManager(this);
        //Get Medicine object to temporary object in memory:
        Medicine tmp_medicine = dbm.dbGetMedicine(record_id);
        dbm.close();

        //Load data to Activity:
        //Load Name:
        editMedicine.setText(tmp_medicine.getName().toString());

        //Load FormMedicine:
        String sSel = tmp_medicine.getFormMedicine();
        rg1.clearCheck();
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);

        //Set Check radiobutton:
        for (int i = 0; i<rg1.getChildCount(); i++ ){
            RadioButton child = (RadioButton) rg1.getChildAt(i);
            String sText = child.getText().toString();      //Read Text
            //Log.d("DB", "Szukamy:" + sSel + " a w RadoButton jest: " + sText );
            if (sText.equals(sSel)){
                child.setChecked(true);         //Set this RadioButton as checked
                //Log.d("DB", "Selected:" + sText );
            }
        }
        for (int i = 0; i<rg2.getChildCount(); i++ ){
            RadioButton child = (RadioButton) rg2.getChildAt(i);
            String sText = child.getText().toString();      //Read Text
            //Log.d("DB", "Szukamy:" + sSel + " a w RadoButton jest: " + sText );
            if (sText.equals(sSel)){
                child.setChecked(true);         //Set this RadioButton as checked
                //Log.d("DB", "Selected:" + sText );
            }
        }


        // Load Quantity:
        editQuantity.setText(tmp_medicine.getQuantity().toString());

        // Load DoseType:
        String sTMP_DoseType = tmp_medicine.getDose_option();
        for (int i = 0; i<spinner.getCount(); i++ ){
            String sTextDoseOption = spinner.getItemAtPosition(i).toString();    //Read Text Value
            if (sTextDoseOption.equals(sTMP_DoseType)){
                spinner.setSelection(i);
            }
        }


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


    public void updateMedicineInStore(View view){
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
            newMedicine.set_id(iRec_ID_for_Update);
            newMedicine.setFormMedicine(Medicine.FormMedInt2Str(realCheck));
            newMedicine.setDose_option(Medicine.DoseOptionInt2Str(iSpinner));
            newMedicine.setQuantity(iQuantity);         //quantity

            //Saving to Database:
            DataBaseManager dbm = new DataBaseManager(this);
            dbm.dbUpdateMedicine(newMedicine);
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

            //Hide virtual keyboard:
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }


            //finishing Activity
            finish();

        } else if (medicineName.matches("")) {
            Toast.makeText(this,"Please provide Medicine Name",Toast.LENGTH_SHORT).show();
        } else if (quantity.matches("")){
            Toast.makeText(this,"Please provide Quantity of Medicine",Toast.LENGTH_SHORT).show();
        } else if (chkId1 == 0 && chkId2 == 0){
            Toast.makeText(this,"Please select proper Type of Medicine",Toast.LENGTH_SHORT).show();
        }
    }
}
