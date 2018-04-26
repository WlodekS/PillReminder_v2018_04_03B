package com.pum2018.pillreminder_v2018_04_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
    }
    
    public void addMedicineToStore(View view){
        Intent addMedicineToStoreIntent = new Intent(StoreActivity.this,AddMedicine.class);
        startActivity(addMedicineToStoreIntent);
    }

    public void deleteMedicineToStore(View view){
        //Toast.makeText(this,"AAAA",Toast.LENGTH_SHORT).show();
        Intent deleteMedicineToStoreIntent = new Intent(StoreActivity.this,DeleteMedicine.class);
        startActivity(deleteMedicineToStoreIntent);
    }

    public void checkMedicineInStore(View view){
        Toast.makeText(this,"checkMedicine",Toast.LENGTH_SHORT).show();
        Intent checkMedicineToStoreIntent = new Intent(StoreActivity.this,CheckMedicine.class);
        startActivity(checkMedicineToStoreIntent);

    }

}
