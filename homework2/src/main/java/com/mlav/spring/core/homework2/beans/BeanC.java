package com.mlav.spring.core.homework2.beans;


public class BeanC implements Validator{
    private String name;
    private int value;

    public BeanC(String name, int value){
        this.name = name;
        this.value = value;
    }


    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    void customInitMethod() {
        System.out.println("beanC: inside customInitMethod");

    }

    void customDestroyMethod() {
        System.out.println("beanC: inside customDestroyMethod");
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
