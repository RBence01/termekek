package com.example.restapi_people;

public class Termek {
    private String nev;
    private int mennyiseg;
    private int darab_ar;
    private String kategoria;
    private int id;

    public Termek(String nev, int mennyiseg, int darab_ar, String kategoria) {
        this.nev = nev;
        this.mennyiseg = mennyiseg;
        this.darab_ar = darab_ar;
        this.kategoria = kategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public int getDarab_ar() {
        return darab_ar;
    }

    public void setDarab_ar(int darab_ar) {
        this.darab_ar = darab_ar;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}
