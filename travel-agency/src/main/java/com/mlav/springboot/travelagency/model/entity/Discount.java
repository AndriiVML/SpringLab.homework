package com.mlav.springboot.travelagency.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Discount extends Entity {
    private Integer step;
    private Integer max;

    public Discount() {
        setId(0);
        step = 5;
        max = 20;
    }

    public Discount(Integer step, Integer max) {
        setId(0);
        this.step = step;
        this.max = max;
    }
}
