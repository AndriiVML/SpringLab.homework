package com.springboot.homework4.util;

import java.math.BigDecimal;
import java.util.UUID;

public class Util {
    /*
     * function designed for generating id, like id is given from the database
     * */
    public static Long generateUniqueId() {
        long val = -1;
        do {
            val = UUID.randomUUID().getMostSignificantBits();
        } while (val < 0);
        return val;
    }

    /*
    * function designed for calculating price based on tour price multiplied on quantity considering user discount.
    * */
    public static BigDecimal getActualPrice(BigDecimal price, int discount, int quantity) {
        double disc = discount / 100.;
        double x = 1 - disc;
        BigDecimal result = price.multiply(BigDecimal.valueOf(x * quantity));
        return result;
    }

}
