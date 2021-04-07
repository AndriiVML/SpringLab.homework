package com.mlav.spring.core.homework2.beans;



public class BeanB implements Validator{
    private String name;
    private int value;

    public BeanB(String name, int value){
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    void customInitMethod() {
        System.out.println("beanB: inside customInitMethod");

    }


    void customInitMethod2() {
        System.out.println("beanB: inside customInitMethod2");

    }

    void customDestroyMethod() {
        System.out.println("beanB: inside customDestroyMethod");
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
