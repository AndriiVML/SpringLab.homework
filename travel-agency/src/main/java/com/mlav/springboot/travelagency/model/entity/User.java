package com.mlav.springboot.travelagency.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends Account{
    private String firstName;
    private String lastName;
    private boolean isBlocked;
    private String email;
    private int discount;

}
