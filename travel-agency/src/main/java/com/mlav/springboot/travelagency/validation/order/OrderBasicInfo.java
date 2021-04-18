package com.mlav.springboot.travelagency.validation.order;

import com.mlav.springboot.travelagency.dto.OrderDto;

import javax.validation.GroupSequence;

@GroupSequence({OrderDefaultAnnotations.class,OrderCustomAnnotations.class})
public interface OrderBasicInfo {
}
