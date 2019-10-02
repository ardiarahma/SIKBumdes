package com.ardiarahma.sik_bumdesa.networks.models;

/**
 * Created by Windows 10 on 8/11/2019.
 */

public class User {

    private String company_name;
    private int company_telp;
    private String company_address;
    private String company_email;
    private String company_pass;

    public User(String company_name, String company_address, int company_telp, String company_email, String company_pass) {
        this.company_name = company_name;
        this.company_address = company_address;
        this.company_telp = company_telp;
        this.company_email = company_email;
        this.company_pass = company_pass;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public int getCompany_telp() {
        return company_telp;
    }

    public void setCompany_telp(int company_telp) {
        this.company_telp = company_telp;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getCompany_pass() {
        return company_pass;
    }

    public void setCompany_pass(String company_pass) {
        this.company_pass = company_pass;
    }
}
