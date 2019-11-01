package model;

import javafx.beans.property.IntegerProperty;

public class Utils {
    public static int fact(Integer x) {
        int product = 1;
        for ( int j=1; j<=x; j++ ) {
            product *= j;
        }
        return product;
    }
}