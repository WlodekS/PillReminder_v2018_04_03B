package com.pum2018.pillreminder_v2018_04_03.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pum2018.pillreminder_v2018_04_03.DataModel.FormMedicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wlodek on 2018-04-04.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    // Database name:
    private static final String DB_NAME = "PillRemDB";
    // Database version:
    private static final int DB_VERSION = 7;

    //--------------
    // TABLE NAMES :
    //--------------
    private static final String MEDICINE_TABLE = "medicines";
    private static final String TAKINGS_PLAN_TABLE = "takingsplan";
    //private static final String DOSETYPE_TABLE = "dosetypes";
    //private static final String STORE_TABLE = "store";
    //private static final String REPORT_TABLE = "reports";
    //private static final String MESSAGES_TABLE = "messages";

    //----------------
    // TABLE COLUMNS :
    //----------------
    // MEDICINE table columns:
    private static final String MED_KEY_ID = "_id";
    private static final String MED_KEY_NAME = "name";
    private static final String MED_KEY_FORM = "form_medicine";
    private static final String MED_KEY_QUANTITY = "quantity";
    private static final String MED_KEY_DOSE_OPTION = "dose_option";


    // TAKINGS_PLAN_TABLE table columns:
    private static final String TPT_KEY_ID = "_id";
    private static final String TPT_KEY_HOUR = "hour";
    private static final String TPT_KEY_MINUTE = "minute";
    private static final String TPT_KEY_MEDICINE_ID = "medicine_id";
    private static final String TPT_KEY_DOSE = "dose";
    //private static final String TPT_KEY_DOSETYPE_ID = "dosetype_id";

//    // DOSETYPE table columns:
//    private static final String DOS_KEY_ID = "_id";
//    private static final String DOS_KEY_NAME = "name";
//
//    // STORE table columns:
//    private static final String STO_KEY_ID = "_id";
//    private static final String STO_KEY_MEDICINE_ID = "medicine_id";
//    private static final String STO_KEY_QUANTITY = "quantity";
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
                    + MED_KEY_FORM + " CHAR(30),"
                    + MED_KEY_DOSE_OPTION + " CHAR(15),"
                    + MED_KEY_QUANTITY + " INTEGER"
                    + ");";

    // Taking Table - create string:
    private static final String CREATE_TAKINGS_PLAN_TABLE =
            "CREATE TABLE if not exists " + TAKINGS_PLAN_TABLE + " ("
                    + TPT_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + TPT_KEY_HOUR + " INTEGER,"
                    + TPT_KEY_MINUTE + " INTEGER, "
                    + TPT_KEY_MEDICINE_ID + " INTEGER NOT NULL,"
                    + TPT_KEY_DOSE + " INTEGER,"
                    + "FOREIGN KEY (" + TPT_KEY_MEDICINE_ID + ") REFERENCES " + MEDICINE_TABLE + "(" + MED_KEY_ID + ") "
                    + ");";


    // Constructor:
    public DataBaseManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating tables:
        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_TAKINGS_PLAN_TABLE);
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
        values.put(MED_KEY_DOSE_OPTION, medicine.getDose_option());
        values.put(MED_KEY_QUANTITY, medicine.getQuantity());

        //Insert row:
        long medicine_id = db.insertOrThrow(MEDICINE_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + MEDICINE_TABLE  + ". Record added. _id= " + medicine_id);

        return medicine_id;
    }



    /**
     * dbCreateTakingPlan
     * takes a TakingPlan object and inserts the data into the database
     * Param1: store - Store object
     * Param2: quantity - Integer. Number of medicine in the store
     * Return: _id (long) generated by the database for the added taking
     */
    public long dbCreateTakingPlan(TakingsPlan takingsPlan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TPT_KEY_ID, takingsPlan.get_id());
        values.put(TPT_KEY_HOUR, takingsPlan.getHour());
        values.put(TPT_KEY_MINUTE, takingsPlan.getMinute());
        values.put(TPT_KEY_MEDICINE_ID, takingsPlan.getMedicine_id());
        values.put(TPT_KEY_DOSE, takingsPlan.getDose());

        //Insert row:
        long takingsPlan_id = db.insertOrThrow(TAKINGS_PLAN_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKINGS_PLAN_TABLE  + ". Record added. _id= " + takingsPlan_id);

        return takingsPlan_id;
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
    public Medicine dbGetMedicine(Integer id) {
        String sSelectString = "SELECT * FROM " +
                MEDICINE_TABLE + " WHERE "      +
                MED_KEY_ID   + " = " + id;
        Medicine medicine = new Medicine();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            medicine.set_id(cursor.getInt(0));
            medicine.setName(cursor.getString(1));
            //medicine.setFormMedicine(cursor.getInt(2));
            medicine.setFormMedicine(cursor.getString(2));
            //medicine.setDose_option(cursor.getInt(3));
            medicine.setDose_option(cursor.getString(3));

            medicine.setQuantity(cursor.getInt(4));
        }
        cursor.close();
        return medicine;
    }

    /**
     * dbGetTakingsPlan
     * Get a Taking object from the table TAKING_TABLE
     * Param1: id of TakingsPlan object
     * Return: TakingsPlan object
     */
    public TakingsPlan dbGetTakingsPlan(Integer id) {
        String sSelectString = "SELECT * FROM " +
                TAKINGS_PLAN_TABLE + " WHERE "      +
                TPT_KEY_ID   + " = " + id;
        TakingsPlan takingsPlan = new TakingsPlan();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            takingsPlan.set_id(cursor.getInt(0));
            takingsPlan.setHour(cursor.getInt(1));
            takingsPlan.setMinute(cursor.getInt(2));
            takingsPlan.setMedicine_id(cursor.getInt(3));
            takingsPlan.setDose(cursor.getInt(4));
        }
        cursor.close();
        return takingsPlan;
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
                medicine.set_id(cursor.getInt(0));
                medicine.setName(cursor.getString(1));
                //medicine.setFormMedicine(cursor.getInt(2));
                medicine.setFormMedicine(cursor.getString(2));
                //medicine.setDose_option(cursor.getInt(3));
                medicine.setDose_option(cursor.getString(3));
                medicine.setQuantity(cursor.getInt(4));

                medicines.add(medicine);
            }
        }
        cursor.close();
        return medicines;
    }

    /**
     * dbGetAllMedicines2
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
                medicine.set_id(cursor.getInt(0));
                medicine.setName(cursor.getString(1));
                //medicine.setFormMedicine(cursor.getInt(2));
                medicine.setFormMedicine(cursor.getString(2));
                //medicine.setDose_option(cursor.getInt(3));
                medicine.setDose_option(cursor.getString(3));
                medicine.setQuantity(cursor.getInt(4));

                medicines.add(medicine);
            }
        }
        cursor.close();
        return medicines;
    }

    /**
     * dbGetAllTakingsPlans
     * Get all TakingsPlan rows from table TAKINGS_PLAN_TABLE
     * Params: no params
     * Return: list of TakingsPlan objects
     */
    public List<TakingsPlan> dbGetAllTakingsPlans() {
        List<TakingsPlan> takingsPlans = new ArrayList<>();
        String selectString = "SELECT * FROM " + TAKINGS_PLAN_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                TakingsPlan takingsPlan = new TakingsPlan();
                takingsPlan.set_id(cursor.getInt(0));
                takingsPlan.setHour(cursor.getInt(1));
                takingsPlan.setMinute(cursor.getInt(2));
                takingsPlan.setMedicine_id(cursor.getInt(3));
                takingsPlan.setDose(cursor.getInt(4));
                takingsPlans.add(takingsPlan);
            }
        }
        cursor.close();
        return takingsPlans;
    }

    /**
     *
     * dbGetAllTakingsPlansForListView
     * Get all needed information from two tables:
     * - TAKINGS_PLAN_TABLE
     * - MEDICINE_TABLE
     * Params: no params
     * Return: list of medicine objects
     */
    public ArrayList<TakingsPlan> dbGetAllTakingsPlansForListView() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        String selectString = "SELECT * FROM " + MEDICINE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Medicine medicine = new Medicine();
                medicine.set_id(cursor.getInt(0));
                medicine.setName(cursor.getString(1));
                //medicine.setFormMedicine(cursor.getInt(2));
                medicine.setFormMedicine(cursor.getString(2));
                //medicine.setDose_option(cursor.getInt(3));
                medicine.setDose_option(cursor.getString(3));
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

    /**
     * dbUpdateTakingsPlan
     * Update a dbUpdateTakingsPlan object in the table TAKINGS_PLAN_TABLE
     * Param1: Taking object
     * Return: nothing
     */
    public void dbUpdateTakingsPlan(TakingsPlan takingsPlan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TPT_KEY_ID, takingsPlan.get_id());
        values.put(TPT_KEY_HOUR, takingsPlan.getHour());
        values.put(TPT_KEY_MINUTE, takingsPlan.getMinute());
        values.put(TPT_KEY_MEDICINE_ID, takingsPlan.getMedicine_id());
        values.put(TPT_KEY_DOSE, takingsPlan.getDose());
        //Put _id of store in the list of arguments:
        String[] args = {String.valueOf(takingsPlan.get_id())};
        //Update row:
        db.update(TAKINGS_PLAN_TABLE, values, TPT_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKINGS_PLAN_TABLE  + ". Record updated. _id= " + takingsPlan.get_id());
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


    /**
     * dbDeleteTakingsPlan
     * delete a TakingsPlan object in the table TAKINGS_PLAN_TABLE by his _id
     * Param1: _id of TakingsPlan object
     * Return: nothing
     */
    public void dbDeleteTakingsPlan(long takingsPlanID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + takingsPlanID};
        //delete row where _id=takingID:
        db.delete(TAKINGS_PLAN_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKINGS_PLAN_TABLE  + ". Record deleted. _id= " + takingsPlanID);
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

    public void TEST_TypeTableTakongsPlan(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "------------------------------");
        Log.d("DB", "Content of Tables TakingsPlan :");
        Log.d("DB", "------------------------------");
        for(Medicine m: dbGetAllMedicines() ){ Log.d("DB", m.toString());  }
        if(dbGetAllMedicines().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "----------------------------");
    }

}
