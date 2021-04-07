package com.mlav.spring.core.homework2.beans;


public class BeanD implements Validator{
    private String name;
    private int value;


    public BeanD(String name, int value){
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }


    void customInitMethod() {
        System.out.println("beanD: inside customInitMethod");

    }

    void customDestroyMethod() {
        System.out.println("beanD: inside customDestroyMethod");
    }


    @Override
    public void validate() {
        StringBuilder sb = new StringBuilder();
        if (name == null) {
            sb.append("Name is null. ");
        }
        if (value <= 0) {
            sb.append("Value is not positive. ");
        }
        if (sb.length() != 0) {
            System.out.println(" is invalid: " + sb);
            return;
        }
        System.out.println(" is valid.");
    }
}
