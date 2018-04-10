package com.pum2018.pillreminder_v2018_04_03.DataModel;

/**
 * Created by Wlodek on 2018-04-04.
 */

public class Medicine {
    private Integer _id;
    private String name;
    private String formMedicine;
    private Integer quantity;
    private String dose_option;


    //-------------
    //Constructors:
    //-------------
    //with no params:
    public Medicine() {
    }

    //without _id:
    public Medicine(String name, String formMedicine, Integer quantity, String dose_option) {
        this.name = name;
        this.formMedicine = formMedicine;
        this.quantity = quantity;
        this.dose_option = dose_option;
    }

    //with all params:

    public Medicine(Integer _id, String name, String formMedicine, Integer quantity, String dose_option) {
        this._id = _id;
        this.name = name;
        this.formMedicine = formMedicine;
        this.quantity = quantity;
        this.dose_option = dose_option;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormMedicine() {
        return formMedicine;
    }

    public void setFormMedicine(String formMedicine) {
        this.formMedicine = formMedicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDose_option() {
        return dose_option;
    }

    public void setDose_option(String dose_option) {
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
                ", formMedicine='" + formMedicine + '\'' +
                ", quantity=" + quantity +
                ", dose_option='" + dose_option + '\'' +
                '}';
    }

    //--------------------------------
    // Other method for FormMedicine :
    //--------------------------------
    //Int2String converter:
    public static String FormMedInt2Str(Integer intValue){
        String sRet="";

        switch(intValue){
            case 1:
                sRet = "Aerosol";
                break;
            case 2:
                sRet = "Capsule";
                break;
            case 3:
                sRet = "Drops";
                break;
            case 4:
                sRet = "Globule";
                break;
            case 5:
                sRet = "Injection";
                break;
            case 6:
                sRet = "Insuline";
                break;
            case 7:
                sRet = "Plaster";
                break;
            case 8:
                sRet = "SublingualTablet";
                break;
            case 9:
                sRet = "Suppository";
                break;
            case 10:
                sRet = "Syrup";
                break;
            case 11:
                sRet = "Tablet";
                break;

            default: //For all other cases:
                sRet = "";
                break;
        }
        return sRet;
    }

    //String2Integer converter:
    public static Integer FormMedStr2Int(String strValue){
        Integer iRet = 0;

        switch(strValue){
            case "Aerosol":
                iRet = 1;
                break;
            case "Capsule":
                iRet = 2;
                break;
            case "Drops":
                iRet = 3;
                break;
            case "Globule":
                iRet = 4;
                break;
            case "Injection":
                iRet = 5;
                break;
            case "Insuline":
                iRet = 6;
                break;
            case "Plaster":
                iRet = 7;
                break;
            case "SublingualTablet":
                iRet = 8;
                break;
            case "Suppository":
                iRet = 9;
                break;
            case "Syrup":
                iRet = 10;
                break;
            case "Tablet":
                iRet = 11;
                break;

            default: //For all other cases:
                iRet = 0;
                break;
        }
        return iRet;
    }


    //-------------------------------
    // Other method for Dose_Option :
    //-------------------------------
    //Int2String converter:
    public static String DoseOptionInt2Str(int intValue){
        String sRet="";

        switch(intValue){
            case 0:
                sRet = "ml";
                break;
            case 1:
                sRet = "szt";
                break;
            case 2:
                sRet = "krople";
                break;

            default: //For all other cases:
                sRet = "";
                break;
        }
        return sRet;
    }

    //String2Integer converter:
    public static int DoseOptionStr2Int(String strValue){
        Integer iRet = 0;

        switch(strValue){
            case "ml":
                iRet = 0;
                break;
            case "szt":
                iRet = 1;
                break;
            case "krople":
                iRet = 2;
                break;

            default: //For all other cases:
                iRet = 0;
                break;
        }
        return iRet;
    }
}
