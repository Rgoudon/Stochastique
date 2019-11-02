package model;

import javafx.beans.property.StringProperty;

public class Utils {
    public static int fact(Integer x) {
        int product = 1;
        for ( int j=1; j<=x; j++ ) {
            product *= j;
        }
        return product;
    }

    public static String format(float value, String timeUnit) {
        String valueWithUnit;
        switch(timeUnit) {
            case "Milliseconde":
                valueWithUnit = String.valueOf(value / 1000) + " ms";
                break;
            case "Seconde":
                valueWithUnit = String.valueOf(value) + " s";
                break;
            case "Minute":
                valueWithUnit = String.valueOf(value * 60) + "s";
                break;
            case "Heure":
                valueWithUnit = String.valueOf(value * 60) + " min";
                break;
            default:
                valueWithUnit = String.valueOf(value);
        }

        return valueWithUnit;
    }
}