package model;

public class Utils {
    public static int fact(int x) {
        int product = 1;
        for ( int j=1; j<=x; j++ ) {
            product *= j;
        }
        return product;
    }
}