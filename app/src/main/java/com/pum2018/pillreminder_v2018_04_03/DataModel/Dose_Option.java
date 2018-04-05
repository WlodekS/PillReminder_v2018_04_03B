package com.pum2018.pillreminder_v2018_04_03.DataModel;

/**
 * Created by Wlodek on 2018-04-04.
 */


/*
* Konstrucja enum w ten sposób umożliwia użycie w następujący (wygodny) sposób:
* Dose_Options opt1 = Dose_Options.CAPSULE;
* wartość opt2 to: 2 (odpowiednia liczba typu Dose_Options)
* */

public enum Dose_Option {
    ml(1),
    sztuki(2),
    krople(3);

    private final int value;

    private Dose_Option(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
