package com.springboot.homework4.model.entity;

import lombok.Data;

@Data
public class Discount extends Entity{
    private int step=5;
    private int max=20;
}
