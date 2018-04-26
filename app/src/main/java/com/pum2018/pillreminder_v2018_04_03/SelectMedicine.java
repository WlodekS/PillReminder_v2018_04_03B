package com.pum2018.pillreminder_v2018_04_03;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter_SelectMedicine;
import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter_StoreList;
import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingAdapter;
import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Taking;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class SelectMedicine extends AppCompatActivity {

    private DataBaseManager dbm;
    // MulitiLine adapter:
    private MedicineAdapter_SelectMedicine mAdapter2;

    //variables to access listView objects:
    ListView lv;

    //Informacje o bieżąco edytowanym rekordzie w tablicy Alarmów:
    Integer currRec_Alarm_ID;
    Integer currRec_Alarm_Medicine_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_medicine);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //Odczytamy wartościprzekazane w Extras. Ustawimy tez dwie zmienne klasy, tak, żeby
        //Informacje o edytowanym rekordzie Alarmu oraz Medicine_ID były łatwo dostępne w całej klasie:
        //get the intent in the target activity
        Intent intent = getIntent();

        //get the attached bundle from the intent
        Bundle extras = intent.getExtras();

        //Extracting and saving the stored data from the bundle
        currRec_Alarm_ID = extras.getInt("ALARM_ID");
        currRec_Alarm_Medicine_ID = extras.getInt("MEDICINE_ID");

        //initialize DataBase Manager for Activity:
        dbm = new DataBaseManager(this);

        //initialize variables to access activity objects:
        lv = (ListView)findViewById(R.id.listView_SelectMedicine);

        updateUI_SelctMedicine();

    }



    // Update User Interface:
    private void updateUI_SelctMedicine() {
        ArrayList<String> scheduleList = new ArrayList<>();
        SQLiteDatabase db = dbm.getReadableDatabase();

        //jako parametr do wyświetlenia potrzebna jest ArrayList-a obiektów TakingsPlanViewForAdapter (to są te scheduler-y):
        //ArrayList<Medicine> values = dbm.dbGetAllMedicines2();
        ArrayList<Medicine> values = dbm.dbGetAllMedicines2();
        //Jest to lista wygenerowana z jednej tabelki)


        if (mAdapter2 == null) {
            //mAdapter2 = new MedicineAdapter_Check(this, values);
            mAdapter2 = new MedicineAdapter_SelectMedicine(this, values);
            lv.setAdapter(mAdapter2);
        } else {
            mAdapter2.clear();
            mAdapter2.addAll(values);
            mAdapter2.notifyDataSetChanged();
        }
        db.close();
    }

    @Override
    protected void onResume() {
        // Menu: Code\Generate\Override methods\-Wskazujemy metodę OnResume
        super.onResume();

        //For update ListView after Update records:
        mAdapter2.notifyDataSetChanged();
        updateUI_SelctMedicine();
    }

    public void medicine_SEL(View view){

/*        //KOD ZLY !!!
        View parent = (View) view.getParent();
        TextView textView_currID = (TextView) parent.findViewById(R.id.tv_rs_ID);
        String id_String = String.valueOf(textView_currID.getText());
        Integer id_Int = Integer.parseInt(id_String);       // wartość _id jako Integer
        Log.d("DB", "Zmiana Medicine 1 = " + id_String);*/

        //KOD POPRAWNY !!!
        //Zmienne do dostępu do obiektów na ekranie - Odczytujemy parametry klikniętego rekordu:
        TextView textView_currRec_Medicine_ID = (TextView)view.findViewById(R.id.tv_rs_ID);
        String id_String_currMedicine_ID = String.valueOf(textView_currRec_Medicine_ID.getText());
        Integer id_Int_curr_Medicine_ID = Integer.parseInt(id_String_currMedicine_ID);       // wartość _id jako Integer
        //Log.d("DB", "Zmiana Medicine 2 (Medicine_ID wybranego Medicine) = " + id_String_currMedicine_ID);
        //Tutaj mam numer nowo wybranego rekordu z tabeli Medicine.




        //W zależności od tego czy wybrano Medicine - to samoczy inne:
        //Przygotowujemy informację zwrotną do Activity "Update Schedule":
        //create a Bundle object
        Bundle extras = new Bundle();
        if (currRec_Alarm_Medicine_ID==id_Int_curr_Medicine_ID) {
            //Wybrano to samo Medicine
            //Adding key value pairs to this bundle
            extras.putBoolean("SELECTED_TE_SAME", true);
            //Log.d("DB", "Wyslano wybor Medicine-TRUE" );
        } else {
            //Pobieram wybrany obiekt Medicine z bazy:
            Medicine selMed = dbm.dbGetMedicine(id_Int_curr_Medicine_ID);
            //Wybrano inne Medicine=>zmienimy Activity "Update Schedule":
            //Adding key value pairs to this bundle
            extras.putBoolean("SELECTED_TE_SAME", false);
            //Dodatkowo w extras należy umieścić następujące informacje o nowym Medicine
            // Medicine_ID:
            extras.putInt("KEY_MEDICINE_ID", id_Int_curr_Medicine_ID);
            // Medicine Name:
            extras.putString("KEY_MEDICINE_NAME", selMed.getName());
            // FormMedicine:
            extras.putString("KEY_MEDICINE_FORM", selMed.getFormMedicine());
            // SetAlarmDoseOption:
            extras.putString("KEY_MEDICINE_DOSE_OPTION", selMed.getDose_option());

            //Log.d("DB", "Wyslano wybor Medicine-FALSE" );
        }

        //Odczyt numeru Medicine_ID wyświetlanego na Activity edycji alarmu:
        //TextView tv_ID_Of_Medicine_On_ScheduleUpdate = (TextView) view.findViewById(R.id.textView_Medicine_ID);
        //String id_String_Medicine_ID = String.valueOf(textView_currMedicine_ID.getText());
        //Integer id_Int_currMedicine_ID = Integer.parseInt(id_String_Medicine_ID);       // wartość _id jako Integer
        //Log.d("DB", "Zmiana Medicine 3 (Poprzednie Medicine_ID)= " + id_String_Medicine_ID);


        //Odczytujemy też pozostałe parametry - są przecież takie same jak w bazie danych:
    //    TextView textView_curr_MedicineName = (TextView)findViewById(R.id.tv_rs_MedicineName);
    //    TextView textView_curr_MedicineForm = (TextView)findViewById(R.id.tv_rs_MedicineForm);
    //    TextView textView_curr_DoseOption = (TextView)findViewById(R.id.tv_rs_DoseOption);


        //Jeśli wykonano jakąś zmianę (wybrano inny rekord)-najłatwiej wykonać wpis do bazy
        //i odświeżyć dane w ListView:
        //Odczytujemy id dla wybranego Medicine:
//        Log.d("DB", "Zmiana Medicine.   Poprzednie Medicine ID=" + id_String);
//        Log.d("DB", "Zmiana Medicine. Nowo wybrane Medicine ID=" + id_String_currMedicine_ID);

        //TextView aa = (TextView)findViewById(R.id.textView_Alarm_ID);
        //String alarm_ID = (String) aa.getText();
        //Log.d("DB", "Zmiana Medicine. 3: " + alarm_ID);


        //Ustawiamy wartości na Activity ScheduleNewUpdateActivity:
        //Zmienne dostępu do Activity rodzica:
        //TextView parentTv_currID = (TextView) parent.findViewById(R.id.tv_rs_ID);

        //Kończymy pracę w Activity - przekażemy zwrotnie numer rekordu w tabeli MEDICINE_TABLE.
        //odnajdujemy parent parent:

        //Konczymy aktywnosc:
        Intent intentMessage=new Intent();
        // put the message to return as result in Intent
        intentMessage.putExtras(extras);
        // Set The Result in Intent
        setResult(2,intentMessage);
        // finish The activity
        finish();

    }

}
