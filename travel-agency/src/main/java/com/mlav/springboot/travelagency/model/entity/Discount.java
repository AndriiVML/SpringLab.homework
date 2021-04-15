package com.mlav.springboot.travelagency.model.entity;

import lombok.Data;

@Data
public class Discount extends Entity{
    private int step=5;
    private int max=20;
}
