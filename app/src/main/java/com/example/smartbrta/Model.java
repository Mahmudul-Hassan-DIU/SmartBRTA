package com.example.smartbrta;

public class Model {

    private String name;
    private String reg;

    private Model(){

    }

    public Model(String name, String reg) {
        this.name = name;
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }
}
