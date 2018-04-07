package com.pum2018.pillreminder_v2018_04_03.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pum2018.pillreminder_v2018_04_03.DataModel.FormMedicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wlodek on 2018-04-04.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    // Database name:
    private static final String DB_NAME = "PillRemDB";
    // Database version:
    private static final int DB_VERSION = 1;

    // Table names:
    private static final String MEDICINE_TABLE = "medicines";
    //private static final String DOSETYPE_TABLE = "dosetypes";
    //private static final String STORE_TABLE = "store";
    //private static final String TAKING_TABLE = "takings";
    //private static final String REPORT_TABLE = "reports";
    //private static final String MESSAGES_TABLE = "messages";


    // TABLE COLUMNS :
    // MEDICINE table columns:
    private static final String MED_KEY_ID = "_id";
    private static final String MED_KEY_NAME = "name";
    private static final String MED_KEY_FORM = "form_medicine";
    private static final String MED_KEY_QUANTITY = "quantity";
    private static final String MED_KEY_DOSE_OPTION = "dose_option";



//    // DOSETYPE table columns:
//    private static final String DOS_KEY_ID = "_id";
//    private static final String DOS_KEY_NAME = "name";
//
//    // STORE table columns:
//    private static final String STO_KEY_ID = "_id";
//    private static final String STO_KEY_MEDICINE_ID = "medicine_id";
//    private static final String STO_KEY_QUANTITY = "quantity";
//
//    // TAKING table columns:
//    private static final String TAK_KEY_ID = "_id";
//    private static final String TAK_KEY_MEDICINE_ID = "medicine_id";
//    private static final String TAK_KEY_DOSE = "dose";
//    private static final String TAK_KEY_DOSETYPE_ID = "dosetype_id";
//    private static final String TAK_KEY_HOUR = "hour";
//    private static final String TAK_KEY_MINUTE = "minute";
//
//
//    // REPORT table columns:
//    private static final String REP_KEY_ID = "_id";
//    private static final String REP_KEY_MEDICINENAME = "medicineName";
//    private static final String REP_KEY_DATE = "date";
//    private static final String REP_KEY_PLANNEDTIME = "plannedtime";
//    private static final String REP_KEY_ACTUALTIME = "actualtime";
//
//    // MESSAGES table columns:
//    private static final String MES_KEY_ID = "_id";
//    private static final String MES_KEY_TIME = "creatingDateTime";
//    private static final String MES_KEY_TEXT = "text";

    //UWAGA! (W.S.):
    //When Error: E/SQLiteLog: (1) near "AUTOINCREMENT": syntax error
    //Description:
    //There is NO AUTOINCREMENT keyword in SQLite!!

    // Medicine Table - create string (I'm doing it the first because reference to it later)
    private static final String CREATE_MEDICINE_TABLE =
            "CREATE TABLE if not exists " + MEDICINE_TABLE + " ("
                    + MED_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + MED_KEY_NAME + " CHAR(30) NOT NULL,"
                    + MED_KEY_FORM + " INTEGER NOT NULL,"
                    + MED_KEY_DOSE_OPTION + " INTEGER NOT NULL,"
                    + MED_KEY_QUANTITY + " INTEGER NOT NULL"
                    + ");";


    // Constructor:
    public DataBaseManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating tables:
        db.execSQL(CREATE_MEDICINE_TABLE);

        Log.d("DB", "Metoda onCreate - Baza danych zostala utworzona.");
    }

    // TODO: In the PROF. version change it. This version delete old data!
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP tables:
        db.execSQL("DROP TABLE IF EXISTS " + MEDICINE_TABLE);

        Log.d("DB", "Metoda onUpgrade - Tabele w bazie zostaly skasowane.");

        //Creating new version of tables:
        onCreate(db);
    }


    //=====================================
    //     C R E A T E   M E T H O D S
    //=====================================


    /**
     * dbCreateMedicine
     * takes a medicine object and inserts the data into the database
     * Param1: medicine - medicine object
     * Return: _id (long) generated by the database for the added medicine
     */
    public long dbCreateMedicine(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MED_KEY_NAME, medicine.getName());
        values.put(MED_KEY_FORM, medicine.getFormMedicine());
        values.put(MED_KEY_DOSE_OPTION, medicine.getQuantity());
        values.put(MED_KEY_QUANTITY, medicine.getQuantity());


        //Insert row:
        long medicine_id = db.insertOrThrow(MEDICINE_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + MEDICINE_TABLE  + ". Record added. _id= " + medicine_id);

        return medicine_id;
    }


    //========================================
    //     R E A D (GET)    M E T H O D S
    //========================================
    //----------------
    // Get 1 element :
    //----------------
    /**
     * dbGetMedicine
     * Get a Medicine object from the table MEDICINE_TABLE
     * Param1: id of Medicine object
     * Return: Medicine object
     */
    public Medicine dbGetMedicine(Long id) {
        String sSelectString = "SELECT * FROM " +
                MEDICINE_TABLE + " WHERE "      +
                MED_KEY_ID   + " = " + id;
        Medicine medicine = new Medicine();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            medicine.set_id(cursor.getLong(0));
            medicine.setName(cursor.getString(1));
            medicine.setFormMedicine(cursor.getInt(2));
            medicine.setDose_option(cursor.getInt(3));
            medicine.setQuantity(cursor.getInt(4));
        }
        cursor.close();
        return medicine;
    }

    //---------------------------
    // Get list of all elements :
    //---------------------------

    /**
     * dbGetAllMedicines
     * Get all Medicine rows from table MEDICINE_TABLE
     * Params: no params
     * Return: list of medicine objects
     */
    public List<Medicine> dbGetAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String selectString = "SELECT * FROM " + MEDICINE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                Medicine medicine = new Medicine();
                medicine.set_id(cursor.getLong(0));
                medicine.setName(cursor.getString(1));
                medicine.setFormMedicine(cursor.getInt(2));
                medicine.setDose_option(cursor.getInt(3));
                medicine.setQuantity(cursor.getInt(4));

                medicines.add(medicine);
            }
        }
        cursor.close();
        return medicines;
    }

    /**
     * dbGetAllMedicines
     * Get all Medicine rows from table MEDICINE_TABLE as ArrayList (for MedicineAdapter)
     * Params: no params
     * Return: list of medicine objects
     */
    public ArrayList<Medicine> dbGetAllMedicines2() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        String selectString = "SELECT * FROM " + MEDICINE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Medicine medicine = new Medicine();
                medicine.set_id(cursor.getLong(0));
                medicine.setName(cursor.getString(1));
                medicine.setFormMedicine(cursor.getInt(2));
                medicine.setDose_option(cursor.getInt(3));
                medicine.setQuantity(cursor.getInt(4));

                medicines.add(medicine);
            }
        }
        cursor.close();
        return medicines;
    }


    //======================================
    //     U P D A T E    M E T H O D S
    //======================================

    /**
     * dbUpdateMedicine
     * Update a Medicine object in the table MEDICINE_TABLE
     * Param1: Medicine object
     * Return: nothing
     */
    public void dbUpdateMedicine(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MED_KEY_NAME, medicine.getName());
        values.put(MED_KEY_FORM, medicine.getFormMedicine());
        values.put(MED_KEY_DOSE_OPTION, medicine.getDose_option());
        values.put(MED_KEY_QUANTITY, medicine.getQuantity());
        //Put _id of medicine in the list of arguments:
        String[] args = {String.valueOf(medicine.get_id())};
        //Update row:
        db.update(MEDICINE_TABLE, values, MED_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + MEDICINE_TABLE  + ". Record updated. _id= " + medicine.get_id());
    }

    //======================================
    //     D E L E T E    M E T H O D S
    //======================================

    /**
     * dbDeleteMedicine
     * delete a Medicine object in the database by his _id
     * Param1: _id of Medicine object
     * Return: nothing
     */
    public void dbDeleteMedicine(long medicineID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + medicineID};
        //delete row where _id=medicineID:
        db.delete(MEDICINE_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + MEDICINE_TABLE  + ". Record deleted. _id= " + medicineID);
    }



    //==================================
    //     T E S T    M E T H O D S
    //==================================

    public void TEST_TypeTableMedicines(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "----------------------------");
        Log.d("DB", "Content of Tables Medicine :");
        Log.d("DB", "----------------------------");
        for(Medicine m: dbGetAllMedicines() ){ Log.d("DB", m.toString());  }
        if(dbGetAllMedicines().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "----------------------------");
    }


}
