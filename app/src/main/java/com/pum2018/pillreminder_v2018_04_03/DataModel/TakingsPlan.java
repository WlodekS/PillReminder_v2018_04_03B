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
    private Integer day_sunday;
    private Integer day_monday;
    private Integer day_tuesday;
    private Integer day_wednesday;
    private Integer day_thursday;
    private Integer day_friday;
    private Integer day_saturday;



    //-------------
    //Constructors:
    //-------------

    //with no params:
    public TakingsPlan() {
    }

    //main params without _id:
    public TakingsPlan(Integer hour, Integer minute, Integer medicine_id, Integer dose) {
        this.hour = hour;
        this.minute = minute;
        this.medicine_id = medicine_id;
        this.dose = dose;
    }

    //main params with _id:
    public TakingsPlan(Integer _id, Integer hour, Integer minute, Integer medicine_id, Integer dose) {
        this._id = _id;
        this.hour = hour;
        this.minute = minute;
        this.medicine_id = medicine_id;
        this.dose = dose;
    }

    //All params (with days) without _id:

    public TakingsPlan(Integer hour,
                       Integer minute,
                       Integer medicine_id,
                       Integer dose,
                       Integer day_sunday,
                       Integer day_monday,
                       Integer day_tuesday,
                       Integer day_wednesday,
                       Integer day_thursday,
                       Integer day_friday,
                       Integer day_saturday) {
        this.hour = hour;
        this.minute = minute;
        this.medicine_id = medicine_id;
        this.dose = dose;
        this.day_sunday = day_sunday;
        this.day_monday = day_monday;
        this.day_tuesday = day_tuesday;
        this.day_wednesday = day_wednesday;
        this.day_thursday = day_thursday;
        this.day_friday = day_friday;
        this.day_saturday = day_saturday;
    }

    //All params (with days) with _id:

    public TakingsPlan(Integer _id,
                       Integer hour,
                       Integer minute,
                       Integer medicine_id,
                       Integer dose,
                       Integer day_sunday,
                       Integer day_monday,
                       Integer day_tuesday,
                       Integer day_wednesday,
                       Integer day_thursday,
                       Integer day_friday,
                       Integer day_saturday) {
        this._id = _id;
        this.hour = hour;
        this.minute = minute;
        this.medicine_id = medicine_id;
        this.dose = dose;
        this.day_sunday = day_sunday;
        this.day_monday = day_monday;
        this.day_tuesday = day_tuesday;
        this.day_wednesday = day_wednesday;
        this.day_thursday = day_thursday;
        this.day_friday = day_friday;
        this.day_saturday = day_saturday;
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

    public Integer getDay_sunday() {
        return day_sunday;
    }

    public void setDay_sunday(Integer day_sunday) {
        this.day_sunday = day_sunday;
    }

    public Integer getDay_monday() {
        return day_monday;
    }

    public void setDay_monday(Integer day_monday) {
        this.day_monday = day_monday;
    }

    public Integer getDay_tuesday() {
        return day_tuesday;
    }

    public void setDay_tuesday(Integer day_tuesday) {
        this.day_tuesday = day_tuesday;
    }

    public Integer getDay_wednesday() {
        return day_wednesday;
    }

    public void setDay_wednesday(Integer day_wednesday) {
        this.day_wednesday = day_wednesday;
    }

    public Integer getDay_thursday() {
        return day_thursday;
    }

    public void setDay_thursday(Integer day_thursday) {
        this.day_thursday = day_thursday;
    }

    public Integer getDay_friday() {
        return day_friday;
    }

    public void setDay_friday(Integer day_friday) {
        this.day_friday = day_friday;
    }

    public Integer getDay_saturday() {
        return day_saturday;
    }

    public void setDay_saturday(Integer day_saturday) {
        this.day_saturday = day_saturday;
    }

    @Override
    public String toString() {
        return "TakingsPlan{" +
                "_id=" + _id +
                ", hour=" + hour +
                ", minute=" + minute +
                ", medicine_id=" + medicine_id +
                ", dose=" + dose +
                ", day_sunday=" + day_sunday +
                ", day_monday=" + day_monday +
                ", day_tuesday=" + day_tuesday +
                ", day_wednesday=" + day_wednesday +
                ", day_thursday=" + day_thursday +
                ", day_friday=" + day_friday +
                ", day_saturday=" + day_saturday +
                '}';
    }
}
