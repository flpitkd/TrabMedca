package com.example.medca;

public class Especializacao {

    private int id;
    private String nome;

    public Especializacao() {
    }

    public Especializacao(String nome) {
        this.nome = nome;
    }

    public Especializacao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
