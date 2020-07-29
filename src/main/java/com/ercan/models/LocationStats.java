package com.ercan.models;

public class LocationStats {

    private String konum; //state
    private String ulke;  //country
    private int toplamVaka;  //totalCase
    private int birOncekiGundenFarki; //differentPrevDay

    public String getKonum() {
        return konum;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }

    public String getUlke() {
        return ulke;
    }

    public void setUlke(String ulke) {
        this.ulke = ulke;
    }

    public int getToplamVaka() {
        return toplamVaka;
    }

    public void setToplamVaka(int toplamVaka) {
        this.toplamVaka = toplamVaka;
    }

    public int getBirOncekiGundenFarki() {
        return birOncekiGundenFarki;
    }

    public void setBirOncekiGundenFarki(int birOncekiGundenFarki) {
        this.birOncekiGundenFarki = birOncekiGundenFarki;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "konum='" + konum + '\'' +
                ", ulke='" + ulke + '\'' +
                ", toplamVaka=" + toplamVaka +
                ", birOncekiGundenFarki=" + birOncekiGundenFarki +
                '}';
    }
}
