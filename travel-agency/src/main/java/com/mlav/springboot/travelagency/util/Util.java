package com.mlav.springboot.travelagency.util;

import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Util {
    public static final Long DISCOUNT_ID = 1L;
    public static final Integer PAGE_SIZE = 5;

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


    public static List<Sort.Order> getOrdersFromStringArr(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[column, direction]
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return orders;
    }


    public static Sort.Direction getSortDirection(String direction) {
        if (direction.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("Direction can be only asc or desc");
        }
    }

}
