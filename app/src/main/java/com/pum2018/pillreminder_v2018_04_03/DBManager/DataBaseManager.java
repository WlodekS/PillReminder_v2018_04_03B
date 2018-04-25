package com.pum2018.pillreminder_v2018_04_03.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingsPlanViewForAdapter;
import com.pum2018.pillreminder_v2018_04_03.DataModel.FormMedicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Taking;
import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Wlodek on 2018-04-04.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    // Database name:
    private static final String DB_NAME = "PillRemDB";
    // Database version:
    private static final int DB_VERSION = 26;

    //--------------
    // TABLE NAMES :
    //--------------
    private static final String MEDICINE_TABLE = "medicines";
    private static final String TAKINGS_PLAN_TABLE = "takingsplan";
    private static final String RAPORT_TAKINGS_TABLE = "raporttakings";          //Rejestr zażyć medykamentów

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
    private static final String TPT_KEY_DAY_SUN = "day_sun";
    private static final String TPT_KEY_DAY_MON = "day_mon";
    private static final String TPT_KEY_DAY_TUE = "day_tue";
    private static final String TPT_KEY_DAY_WED = "day_wed";
    private static final String TPT_KEY_DAY_THU = "day_thu";
    private static final String TPT_KEY_DAY_FRI = "day_fri";
    private static final String TPT_KEY_DAY_SAT = "day_sat";

    // RAPORT_TAKINGS_TABLE table columns:
    private static final String RTT_KEY_ID = "_id";
    private static final String RTT_KEY_DATE = "date";
    private static final String RTT_KEY_MEDICINE_NAME = "medicine_name";
    private static final String RTT_KEY_PLANNED_TIME = "planned_time";
    private static final String RTT_KEY_TAKINGS_TIME = "taking_time";

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

    // Takings Plan  Table - create string:
    private static final String CREATE_TAKINGS_PLAN_TABLE =
            "CREATE TABLE if not exists " + TAKINGS_PLAN_TABLE + " ("
                    + TPT_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + TPT_KEY_HOUR + " INTEGER,"
                    + TPT_KEY_MINUTE + " INTEGER, "
                    + TPT_KEY_MEDICINE_ID + " INTEGER NOT NULL,"
                    + TPT_KEY_DOSE + " INTEGER,"
                    + TPT_KEY_DAY_SUN + " INTEGER DEFAULT 0,"
                    + TPT_KEY_DAY_MON + " INTEGER DEFAULT 0,"
                    + TPT_KEY_DAY_TUE + " INTEGER DEFAULT 0,"
                    + TPT_KEY_DAY_WED + " INTEGER DEFAULT 0,"
                    + TPT_KEY_DAY_THU + " INTEGER DEFAULT 0,"
                    + TPT_KEY_DAY_FRI + " INTEGER DEFAULT 0,"
                    + TPT_KEY_DAY_SAT + " INTEGER DEFAULT 0,"
                    + "FOREIGN KEY (" + TPT_KEY_MEDICINE_ID + ") REFERENCES " + MEDICINE_TABLE + "(" + MED_KEY_ID + ") "
                    + ");";


    // RAPORT_TAKINGS_TABLE - create string:
    private static final String CREATE_RAPORT_TAKINGS_TABLE =
            "CREATE TABLE if not exists " + RAPORT_TAKINGS_TABLE + " ("
                    + RTT_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + RTT_KEY_DATE + " TEXT NOT NULL,"
                    + RTT_KEY_MEDICINE_NAME + " TEXT NOT NULL,"
                    + RTT_KEY_PLANNED_TIME + " TEXT NOT NULL,"
                    + RTT_KEY_TAKINGS_TIME + " TEXT NOT NULL"
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
        db.execSQL(CREATE_RAPORT_TAKINGS_TABLE);
        Log.d("DB", "Metoda onCreate - Baza danych zostala utworzona.");
    }

    // TODO: In the PROF. version change it. This version delete old data!
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP tables:
        db.execSQL("DROP TABLE IF EXISTS " + MEDICINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TAKINGS_PLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RAPORT_TAKINGS_TABLE);

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
        values.put(TPT_KEY_DAY_SUN, takingsPlan.getDay_sunday());
        values.put(TPT_KEY_DAY_MON, takingsPlan.getDay_monday());
        values.put(TPT_KEY_DAY_TUE, takingsPlan.getDay_tuesday());
        values.put(TPT_KEY_DAY_WED, takingsPlan.getDay_wednesday());
        values.put(TPT_KEY_DAY_THU, takingsPlan.getDay_thursday());
        values.put(TPT_KEY_DAY_FRI, takingsPlan.getDay_friday());
        values.put(TPT_KEY_DAY_SAT, takingsPlan.getDay_saturday());

        //Insert row:
        long takingsPlan_id = db.insertOrThrow(TAKINGS_PLAN_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKINGS_PLAN_TABLE  + ". Record added. _id= " + takingsPlan_id);

        return takingsPlan_id;
    }

    /**
     * dbCreateTakings
     * takes a Takings object and inserts the data into the database
     * Return: _id (long) generated by the database for the added taking
     */
    public long dbCreateTakings(Taking taking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RTT_KEY_ID, taking.get_id());
        values.put(RTT_KEY_DATE, taking.getDate());
        values.put(RTT_KEY_MEDICINE_NAME, taking.getMedicine_name());
        values.put(RTT_KEY_PLANNED_TIME, taking.getPlannedtime());
        values.put(RTT_KEY_TAKINGS_TIME, taking.getTakingtime());

        //Insert row:
        long taking_id = db.insertOrThrow(RAPORT_TAKINGS_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + RAPORT_TAKINGS_TABLE  + ". Record added. _id= " + taking_id);

        return taking_id;
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
     * Get a Taking object from the table TAKINGS_PLAN_TABLE
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
            takingsPlan.setDay_sunday(cursor.getInt(5));
            takingsPlan.setDay_monday(cursor.getInt(6));
            takingsPlan.setDay_tuesday(cursor.getInt(7));
            takingsPlan.setDay_wednesday(cursor.getInt(8));
            takingsPlan.setDay_thursday(cursor.getInt(9));
            takingsPlan.setDay_friday(cursor.getInt(10));
            takingsPlan.setDay_saturday(cursor.getInt(11));
        }
        cursor.close();
        return takingsPlan;
    }

    /**
     * dbGetTaking
     * Get a Taking object from the table TAKING_TABLE
     * Param1: id of TakingsPlan object
     * Return: TakingsPlan object
     */
    public Taking dbGetTaking(Integer id) {
        String sSelectString = "SELECT * FROM " +
                RAPORT_TAKINGS_TABLE + " WHERE "      +
                RTT_KEY_ID   + " = " + id;
        Taking taking = new Taking();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            taking.set_id(cursor.getInt(0));
            taking.setDate(cursor.getString(1));
            taking.setMedicine_name(cursor.getString(2));
            taking.setPlannedtime(cursor.getString(3));
            taking.setTakingtime(cursor.getString(4));
        }
        cursor.close();
        return taking;
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
                takingsPlan.setDay_sunday(cursor.getInt(5));
                takingsPlan.setDay_monday(cursor.getInt(6));
                takingsPlan.setDay_tuesday(cursor.getInt(7));
                takingsPlan.setDay_wednesday(cursor.getInt(8));
                takingsPlan.setDay_thursday(cursor.getInt(9));
                takingsPlan.setDay_friday(cursor.getInt(10));
                takingsPlan.setDay_saturday(cursor.getInt(11));

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
     * as ArrayList (for TakingsPlanAdapter)
     * Params: no params
     * Return: list of medicine objects
     */
    public ArrayList<TakingsPlanViewForAdapter> dbGetAllTakingsPlansForListView() {
        ArrayList<TakingsPlanViewForAdapter> tPVFA = new ArrayList<>();
        //String selectString = "SELECT * FROM " + MEDICINE_TABLE;

        String selectString = "SELECT " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_ID + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_HOUR + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_MINUTE + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_MEDICINE_ID + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DOSE + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DAY_SUN + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DAY_MON + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DAY_TUE + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DAY_WED + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DAY_THU + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DAY_FRI + ", " +
                TAKINGS_PLAN_TABLE + "." + TPT_KEY_DAY_SAT + ", " +
                MEDICINE_TABLE + "." + MED_KEY_NAME + ", " +
                MEDICINE_TABLE + "." + MED_KEY_FORM + ", " +
                MEDICINE_TABLE + "." + MED_KEY_DOSE_OPTION + "  " +
                "FROM " + TAKINGS_PLAN_TABLE + " " +
                "INNER JOIN " + MEDICINE_TABLE + " " +
                "ON " + TAKINGS_PLAN_TABLE + "." + TPT_KEY_MEDICINE_ID + " = " +
                MEDICINE_TABLE + "." + MED_KEY_ID;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                TakingsPlanViewForAdapter tpvfa = new TakingsPlanViewForAdapter();

                tpvfa.set_id(cursor.getInt(0));
                tpvfa.setHour(cursor.getInt(1));
                tpvfa.setMinute(cursor.getInt(2));
                tpvfa.setMedicine_id(cursor.getInt(3));
                tpvfa.setDose(cursor.getInt(4));
                tpvfa.setDay_sunday(cursor.getInt(5));
                tpvfa.setDay_monday(cursor.getInt(6));
                tpvfa.setDay_tuesday(cursor.getInt(7));
                tpvfa.setDay_wednesday(cursor.getInt(8));
                tpvfa.setDay_thursday(cursor.getInt(9));
                tpvfa.setDay_friday(cursor.getInt(10));
                tpvfa.setDay_saturday(cursor.getInt(11));
                tpvfa.setMedicineName(cursor.getString(12));
                tpvfa.setMedKeyfForm(cursor.getString(13));
                tpvfa.setDoseType(cursor.getString(14));

                tPVFA.add(tpvfa);
            }
        }
        cursor.close();
        return tPVFA;
    }

    /**
     *
     * dbGetAllTakings
     * Get all needed information from table:
     * - RAPORT_TAKINGS_TABLE
     * as ArrayList (for TakingAdapter)
     * Params: no params
     * Return: list of Taking objects
     */
    public ArrayList<Taking> dbGetAllTakings() {
        ArrayList<Taking> takings = new ArrayList<>();

        String selectString = "SELECT * FROM " + RAPORT_TAKINGS_TABLE;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Taking taking = new Taking();

                taking.set_id(cursor.getInt(0));
                taking.setDate(cursor.getString(1));
                taking.setMedicine_name(cursor.getString(2));
                taking.setPlannedtime(cursor.getString(3));
                taking.setTakingtime(cursor.getString(4));

                takings.add(taking);
            }
        }
        cursor.close();
        return takings;
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
        values.put(TPT_KEY_DAY_SUN, takingsPlan.getDay_sunday());
        values.put(TPT_KEY_DAY_MON, takingsPlan.getDay_monday());
        values.put(TPT_KEY_DAY_TUE, takingsPlan.getDay_tuesday());
        values.put(TPT_KEY_DAY_WED, takingsPlan.getDay_wednesday());
        values.put(TPT_KEY_DAY_THU, takingsPlan.getDay_thursday());
        values.put(TPT_KEY_DAY_FRI, takingsPlan.getDay_friday());
        values.put(TPT_KEY_DAY_SAT, takingsPlan.getDay_saturday());

        //Put _id of store in the list of arguments:
        String[] args = {String.valueOf(takingsPlan.get_id())};
        //Update row:
        db.update(TAKINGS_PLAN_TABLE, values, TPT_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKINGS_PLAN_TABLE  + ". Record updated. _id= " + takingsPlan.get_id());
    }


    /**
     * dbUpdateTakingsPlan
     * Update a dbUpdateTakingsPlan object in the table TAKINGS_PLAN_TABLE
     * Param1: Taking object
     * Return: nothing
     */
    public void dbUpdateTaking(Taking taking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RTT_KEY_ID, taking.get_id());
        values.put(RTT_KEY_DATE, taking.getDate());
        values.put(RTT_KEY_MEDICINE_NAME, taking.getMedicine_name());
        values.put(RTT_KEY_PLANNED_TIME, taking.getPlannedtime());
        values.put(RTT_KEY_TAKINGS_TIME, taking.getTakingtime());

        //Put _id of store in the list of arguments:
        String[] args = {String.valueOf(taking.get_id())};
        //Update row:
        db.update(TAKINGS_PLAN_TABLE, values, TPT_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKINGS_PLAN_TABLE  + ". Record updated. _id= " + taking.get_id());
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

    /**
     * dbDeleteTakingsPlan
     * delete a TakingsPlan object in the table TAKINGS_PLAN_TABLE by his _id
     * Param1: _id of TakingsPlan object
     * Return: nothing
     */
    public void dbDeleteTaking(Integer takingID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + takingID};
        //delete row where _id=takingID:
        db.delete(RAPORT_TAKINGS_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + RAPORT_TAKINGS_TABLE  + ". Record deleted. _id= " + takingID);
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
        for(TakingsPlan tp: dbGetAllTakingsPlans() ){ Log.d("DB", tp.toString());  }
        if(dbGetAllTakingsPlans().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "----------------------------");
    }

    public void TEST_TypeTableTakings(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "---------------------------");
        Log.d("DB", "Content of Tables TAKINGS :");
        Log.d("DB", "---------------------------");
        for(Taking t: dbGetAllTakings() ){ Log.d("DB", t.toString());  }
        if(dbGetAllTakings().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "----------------------------");
    }

    public void TEST_LoadDataToDataBase(){
        if(dbGetAllMedicines().isEmpty()){
            Log.d("DB", "Load Data to database");
            SQLiteDatabase db = this.getWritableDatabase();
            Medicine med = new Medicine();
            //Medicine 1:
            med.setName("Aspiryna");
            med.setFormMedicine("Tablet");
            med.setQuantity(20);
            med.setDose_option("szt");
            dbCreateMedicine(med);

            //Medicine 2:
            med.setName("Doxycyklina");
            med.setFormMedicine("Tablet");
            med.setQuantity(8);
            med.setDose_option("szt");
            dbCreateMedicine(med);

            //Medicine 3:
            med.setName("Espumisan");
            med.setFormMedicine("Capsule");
            med.setQuantity(50);
            med.setDose_option("szt");
            dbCreateMedicine(med);

            //Medicine 4:
            med.setName("After 50-ty");
            med.setFormMedicine("Drops");
            med.setQuantity(150);
            med.setDose_option("szt");
            dbCreateMedicine(med);


            //Medicine 5:
            med.setName("Diazepan Max");
            med.setFormMedicine("Tablet");
            med.setQuantity(3);
            med.setDose_option("szt");
            dbCreateMedicine(med);

            //Medicine 6:
            med.setName("Nurofen Extra");
            med.setFormMedicine("Drops");
            med.setQuantity(7);
            med.setDose_option("szt");
            dbCreateMedicine(med);

            //Medicine 7:
            med.setName("Gripex 200mg");
            med.setFormMedicine("Capsule");
            med.setQuantity(20);
            med.setDose_option("szt");
            dbCreateMedicine(med);

            //Medicine 8:
            med.setName("Alcepalan");
            med.setFormMedicine("Plaster");
            med.setQuantity(10);
            med.setDose_option("szt");
            dbCreateMedicine(med);

            //Medicine 9:
            med.setName("NeoPalion");
            med.setFormMedicine("Syrup");
            med.setQuantity(20);
            med.setDose_option("ml");
            dbCreateMedicine(med);

            //Medicine 10:
            med.setName("Xanax Forte");
            med.setFormMedicine("Globule");
            med.setQuantity(40);
            med.setDose_option("szt");
            dbCreateMedicine(med);


            //Tabela TAKINGS_PLAN_TABLE:
            //Plan 1:
            TakingsPlan takingsPlan = new TakingsPlan();
            takingsPlan.setHour(8);
            takingsPlan.setMinute(0);
            takingsPlan.setMedicine_id(1);
            takingsPlan.setDose(1);
            dbCreateTakingPlan(takingsPlan);

            //Plan 2:
            takingsPlan.setHour(8);
            takingsPlan.setMinute(0);
            takingsPlan.setMedicine_id(2);
            takingsPlan.setDose(2);
            dbCreateTakingPlan(takingsPlan);

            //Plan 3:
            takingsPlan.setHour(12);
            takingsPlan.setMinute(15);
            takingsPlan.setMedicine_id(3);
            takingsPlan.setDose(5);
            dbCreateTakingPlan(takingsPlan);

            //Plan 4:
            takingsPlan.setHour(18);
            takingsPlan.setMinute(10);
            takingsPlan.setMedicine_id(4);
            takingsPlan.setDose(10);
            takingsPlan.setDay_sunday(1);   //1=true!
            takingsPlan.setDay_monday(1);   //1=true!
            takingsPlan.setDay_tuesday(1);   //1=true!
            dbCreateTakingPlan(takingsPlan);

            //Tabela RAPORT_TAKINGS_TABLE:
            //Taking 1:
            dbCreateTakings(new Taking(
                    "2018-01-01",
                    "Doxycyklina",
                    "08:00",
                    "08:10"));

            //Taking 2:
            dbCreateTakings(new Taking(
                    "2018-01-01",
                    "Gripex 200mg",
                    "12:00",
                    "12:30"));

            //Taking 3:
            dbCreateTakings(new Taking(
                    "2018-01-01",
                    "Espumisan",
                    "18:00",
                    "19:30"));

            //Taking 4:
            dbCreateTakings(new Taking(
                    "2018-01-02",
                    "Doxycyklina",
                    "08:00",
                    "09:30"));

            //Taking 5:
            dbCreateTakings(new Taking(
                    "2018-01-02",
                    "Gripex 200mg",
                    "12:00",
                    "12:10"));

            //Taking 6:
            dbCreateTakings(new Taking(
                    "2018-01-02",
                    "Espumisan",
                    "18:00",
                    "18:30"));

            //Taking 7:
            dbCreateTakings(new Taking(
                    "2018-01-03",
                    "Doxycyklina",
                    "08:00",
                    "08:31"));

            //Taking 8:
            dbCreateTakings(new Taking(
                    "2018-01-03",
                    "Gripex 200mg",
                    "12:00",
                    "12:45"));

            //Taking 9:
            dbCreateTakings(new Taking(
                    "2018-01-03",
                    "Espumisan",
                    "18:00",
                    "19:05"));


            //Taking 10:
            dbCreateTakings(new Taking(
                    "2018-01-04",
                    "Doxycyklina",
                    "08:00",
                    "08:07"));

            //Taking 11:
            dbCreateTakings(new Taking(
                    "2018-01-04",
                    "Gripex 200mg",
                    "12:00",
                    "12:19"));

            //Taking 12:
            dbCreateTakings(new Taking(
                    "2018-01-04",
                    "Espumisan",
                    "18:00",
                    "18:01"));
        }
    }
}
