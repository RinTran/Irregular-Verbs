package com.rintran.irregularverbs;


public class Verbs {
    public Verbs() {
    }

    private String nguyenmau;
    private String quakhu;
    private String quakhuphantu;
    private String nghia;

    public Verbs(String nguyenmau, String quakhu, String quakhuphantu, String nghia) {
        this.nguyenmau = nguyenmau;
        this.quakhu = quakhu;
        this.quakhuphantu = quakhuphantu;
        this.nghia = nghia;
    }



    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public String getNguyenmau() {
        return nguyenmau;
    }

    public void setNguyenmau(String nguyenmau) {
        this.nguyenmau = nguyenmau;
    }

    public String getQuakhu() {
        return quakhu;
    }

    public void setQuakhu(String quakhu) {
        this.quakhu = quakhu;
    }

    public String getQuakhuphantu() {
        return quakhuphantu;
    }

    public void setQuakhuphantu(String quakhuphantu) {
        this.quakhuphantu = quakhuphantu;
    }


}
