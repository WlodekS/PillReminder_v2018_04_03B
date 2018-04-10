package com.pum2018.pillreminder_v2018_04_03.Adapters;

import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;

/**
 * Created by Wlodek on 2018-04-10.
 * Klasa do przedstawienia danych z dwóch tabel w bazie danych. Tabele te to:
 * - TAKINGS_PLAN_TABLE
 * - MEDICINE_TABLE
 * Klasa definiuje obiekt który zostanie wykorzystany w adapterze pobierającym dane z bazy.
 * Klasa wprowadza jedno (potrzebne) dodatkowe pole.
 */
public class TakingsPlanViewForAdapter extends TakingsPlan {
    private String doseType;

    //-------------
    //Constructors:
    //-------------
    //with no params:
    public TakingsPlanViewForAdapter() {
    }

    //without _id:
    public TakingsPlanViewForAdapter(Integer hour, Integer minute, Integer medicine_id, Integer dose, String doseType) {
        super(hour, minute, medicine_id, dose);
        this.doseType = doseType;
    }

    //with all params:
    public TakingsPlanViewForAdapter(Integer _id, Integer hour, Integer minute, Integer medicine_id, Integer dose, String doseType) {
        super(_id, hour, minute, medicine_id, dose);
        this.doseType = doseType;
    }

    @Override
    public String toString() {
        return "TakingsPlan{" +
                "_id=" + super.get_id() +
                ", hour=" + super.getHour() +
                ", minute=" + super.getMinute() +
                ", medicine_id=" + super.getMedicine_id() +
                ", dose=" + super.getDose() +
                ", dosetype" + doseType +
                '}';
    }
}



