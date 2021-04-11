package com.springboot.homework4;

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

}
