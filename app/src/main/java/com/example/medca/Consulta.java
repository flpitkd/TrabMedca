package com.example.medca;

public class Consulta {

    public int id;
    public String nome, data;
    public Especializacao especializacao;


    public Consulta(String s, String s1, String s2) {

    }

    public Consulta(int id, String nome, String data, Especializacao especializacao) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.especializacao = especializacao;
    }


    @Override
    public String toString() {
        return  nome + '\n' + data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Especializacao getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(Especializacao especializacao) {
        this.especializacao = especializacao;
    }
}
