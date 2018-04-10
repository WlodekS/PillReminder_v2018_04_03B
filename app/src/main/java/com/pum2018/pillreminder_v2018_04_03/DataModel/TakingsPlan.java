package com.pum2018.pillreminder_v2018_04_03.DataModel;

/**
 * Created by Wlodek on 2018-04-10.
 * Plan zażywania pojedynczego lekarstwa
 */

public class TakingsPlan {
    private Integer _id;
    private Integer hour;
    private Integer minute;
    private Integer medicine_id;
    private Integer dose;


    //-------------
    //Constructors:
    //-------------

    //with no params:
    public TakingsPlan() {
    }

    //without _id:
    public TakingsPlan(Integer hour, Integer minute, Integer medicine_id, Integer dose) {
        this.hour = hour;
        this.minute = minute;
        this.medicine_id = medicine_id;
        this.dose = dose;
    }

    //with all params:
    public TakingsPlan(Integer _id, Integer hour, Integer minute, Integer medicine_id, Integer dose) {
        this._id = _id;
        this.hour = hour;
        this.minute = minute;
        this.medicine_id = medicine_id;
        this.dose = dose;
    }


    //--------------------
    //Getters and Setters:
    //--------------------

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }


    //----------------
    //toString method:
    //----------------
    @Override
    public String toString() {
        return "TakingsPlan{" +
                "_id=" + _id +
                ", hour=" + hour +
                ", minute=" + minute +
                ", medicine_id=" + medicine_id +
                ", dose=" + dose +
                '}';
    }

}
