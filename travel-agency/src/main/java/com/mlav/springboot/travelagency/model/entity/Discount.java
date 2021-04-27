package com.mlav.springboot.travelagency.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Discount  {
    private Long id;
    private Integer step;
    private Integer max;
}
