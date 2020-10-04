package com.formate.adminhousehold;

class EmpHelperClass {

    String name,phoneNo,aadharNo,spinner;

    public EmpHelperClass() {
    }

    public EmpHelperClass(String name, String phoneNo, String aadharNo, String spinner) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.aadharNo = aadharNo;
        this.spinner = spinner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }
}
