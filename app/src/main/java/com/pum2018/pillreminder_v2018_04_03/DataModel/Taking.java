package com.pum2018.pillreminder_v2018_04_03.DataModel;

/**
 * Created by Wlodek on 2018-04-18.
 */


// TAKINGS_TABLE table columns:


public class Taking {
    private Integer _id;
    private String date;
    private String medicine_name;
    private String plannedtime;
    private String takingtime;

    //-------------
    //Constructors:
    //-------------

    //with no params:

    public Taking() {
    }

    //main params without _id:


    public Taking(String date, String medicine_name, String plannedtime, String takingtime) {
        this.date = date;
        this.medicine_name = medicine_name;
        this.plannedtime = plannedtime;
        this.takingtime = takingtime;
    }

    //main params with _id:

    public Taking(Integer _id, String date, String medicine_name, String plannedtime, String takingtime) {
        this._id = _id;
        this.date = date;
        this.medicine_name = medicine_name;
        this.plannedtime = plannedtime;
        this.takingtime = takingtime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getPlannedtime() {
        return plannedtime;
    }

    public void setPlannedtime(String plannedtime) {
        this.plannedtime = plannedtime;
    }

    public String getTakingtime() {
        return takingtime;
    }

    public void setTakingtime(String takingtime) {
        this.takingtime = takingtime;
    }


    //---------
    //ToString:

    @Override
    public String toString() {
        return "Taking{" +
                "_id=" + _id +
                ", date='" + date + '\'' +
                ", medicine_name='" + medicine_name + '\'' +
                ", plannedtime='" + plannedtime + '\'' +
                ", takingtime='" + takingtime + '\'' +
                '}';
    }
}
