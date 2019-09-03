package com.ardiarahma.sik_bumdesa.database.models;

import java.util.ArrayList;

/**
 * Created by Windows 10 on 8/7/2019.
 */

public class ClassAccount {

    private int id;
    private String name;
    public int level;
    private String posisi_normal;
    public enum STATE{
        CLOSED, OPENED
    }
    public STATE state = STATE.CLOSED;
    public ArrayList<ClassAccount> classAccounts = new ArrayList<>();



    public ClassAccount(){

    }

    public ClassAccount(int id, String name, int level, String posisi_normal) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.posisi_normal = posisi_normal;
    }

    public ArrayList<ClassAccount> getClassAccounts() {
        return classAccounts;
    }

    public void setClassAccounts(ArrayList<ClassAccount> classAccounts) {
        this.classAccounts = classAccounts;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosisi_normal() {
        return posisi_normal;
    }

    public void setPosisi_normal(String posisi_normal) {
        this.posisi_normal = posisi_normal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
