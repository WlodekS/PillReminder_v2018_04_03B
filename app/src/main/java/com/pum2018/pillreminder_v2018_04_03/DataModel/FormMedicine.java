package com.pum2018.pillreminder_v2018_04_03.DataModel;

/**
 * Created by Wlodek on 2018-04-04.
 */

public enum FormMedicine {
    AEROSOL(1),
    CAPSULE(2),
    DROPS(3),
    GLOBULE(4),
    INJECTION(5),
    INSULIN(6),
    PLASTER(7),
    SUBLINGUAL_TABLET(8),
    SUPPOSITORY(9),
    SYRUP(10),
    TABLET(11);

    private final int value;

    private FormMedicine(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}