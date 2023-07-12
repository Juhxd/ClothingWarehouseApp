package com.example.clothingwarehouseapp.model;

public class Armazen {
    //atributos
    private int id;
    private byte ivCamiseta;
    private byte ivCalca;
    private byte ivCalcado;

    //construtor
    public Armazen(int id, byte ivCamiseta, byte ivCalca, byte ivCalcado) {
        this.id = id;
        this.ivCamiseta = ivCamiseta;
        this.ivCalca = ivCalca;
        this.ivCalcado = ivCalcado;
    }
    public Armazen() {
    }
    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getIvCamiseta() {
        return ivCamiseta;
    }

    public void setIvCamiseta(byte ivCamiseta) {
        this.ivCamiseta = ivCamiseta;
    }

    public byte getIvCalca() {
        return ivCalca;
    }

    public void setIvCalca(byte ivCalca) {
        this.ivCalca = ivCalca;
    }

    public byte getIvCalcado() {
        return ivCalcado;
    }

    public void setIvCalcado(byte ivCalcado) {
        this.ivCalcado = ivCalcado;
    }
}
