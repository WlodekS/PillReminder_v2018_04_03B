package com.pum2018.pillreminder_v2018_04_03.DataModel;

/**
 * Created by Wlodek on 2018-04-04.
 */

public class Medicine {
    private Long _id;
    private String name;
    private Integer formMedicine;
    private Integer quantity;
    private Integer dose_option;


    //-------------
    //Constructors:
    //-------------
    //with no params:
    public Medicine() {
    }

    //without _id:

    public Medicine(String name, Integer formMedicine, Integer quantity, Integer dose_option) {
        this.name = name;
        this.formMedicine = formMedicine;
        this.quantity = quantity;
        this.dose_option = dose_option;
    }

    //with all params:

    public Medicine(Long _id, String name, Integer formMedicine, Integer quantity, Integer dose_option) {
        this._id = _id;
        this.name = name;
        this.formMedicine = formMedicine;
        this.quantity = quantity;
        this.dose_option = dose_option;
    }


    //--------------------
    //Getters and Setters:
    //--------------------

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFormMedicine() {
        return formMedicine;
    }

    public void setFormMedicine(Integer formMedicine) {
        this.formMedicine = formMedicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDose_option() {
        return dose_option;
    }

    public void setDose_option(Integer dose_option) {
        this.dose_option = dose_option;
    }


    //----------------
    //toString method:
    //----------------

    @Override
    public String toString() {
        return "Medicine{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", formMedicine=" + formMedicine +
                ", quantity=" + quantity +
                ", dose_option=" + dose_option +
                '}';
    }
}
