package com.mlav.springboot.travelagency.validation.user;


import javax.validation.GroupSequence;

@GroupSequence({UserDefaultAnnotations.class, UserCustomAnnotations.class})
public interface UserRegister {
}
