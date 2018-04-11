package com.pum2018.pillreminder_v2018_04_03.Adapters;

import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;

/**
 * Created by Wlodek on 2018-04-10.
 * Klasa do przedstawienia danych z dwóch tabel w bazie danych. Tabele te to:
 * - TAKINGS_PLAN_TABLE
 * - MEDICINE_TABLE
 * Klasa definiuje obiekt który zostanie wykorzystany w adapterze pobierającym dane z bazy.
 * Klasa wprowadza dwa (potrzebne) dodatkowe pola.
 */
public class TakingsPlanViewForAdapter extends TakingsPlan {
    private String medicineName;
    private String medKeyfForm;
    private String doseType;

    //-------------
    //Constructors:
    //-------------
    //with no params:
    public TakingsPlanViewForAdapter() {
    }

    //without _id:
    public TakingsPlanViewForAdapter(Integer hour, Integer minute, Integer medicine_id, Integer dose, String medicineName, String medKeyfForm, String doseType) {
        super(hour, minute, medicine_id, dose);
        this.medicineName = medicineName;
        this.medKeyfForm = medKeyfForm;
        this.doseType = doseType;
    }

    //with all params:
    public TakingsPlanViewForAdapter(Integer _id, Integer hour, Integer minute, Integer medicine_id, Integer dose, String medicineName, String medKeyfForm, String doseType) {
        super(_id, hour, minute, medicine_id, dose);
        this.medicineName = medicineName;
        this.medKeyfForm = medKeyfForm;
        this.doseType = doseType;
    }


    //--------------------
    //Getters and Setters:
    //--------------------
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String currMedicineName) {
        medicineName = currMedicineName;
    }

    public String getMedKeyfForm() {
        return medKeyfForm;
    }

    public void setMedKeyfForm(String medKeyfForm) {
        this.medKeyfForm = medKeyfForm;
    }

    public String getDoseType() {
        return doseType;
    }

    public void setDoseType(String doseType) {
        this.doseType = doseType;
    }

    //----------------
    //toString method:
    //----------------
    @Override
    public String toString() {
        return "TakingsPlanViewForAdapter{" +
                "MedicineName='" + medicineName + '\'' +
                ", doseType='" + doseType + '\'' +
                "} " + super.toString();
    }

}



