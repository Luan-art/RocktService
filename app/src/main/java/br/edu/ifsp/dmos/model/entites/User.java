package br.edu.ifsp.dmos.model.entites;

import java.sql.Date;

public class User {

    private String nome;
    private String email;
    private String doc;
    private Date dataNasc;
    private String usuario;
    private String telCel;
    private String endereco;
    private String cidade;
    private String estado;
    private String senha;
    private double notaProfissional;
    private int totalNotas;

    private double notaProfissionalAcumulada;

    public User(String nome, String email, String doc, Date dataNasc, String usuario, String telCel,
                String endereco, String cidade, String estado, String senha, double notaProfissional, int totalNotas) {

        setNome(nome);
        setEmail(email);
        setDoc(doc);
        setDataNasc(dataNasc);
        setUsuario(usuario);
        setTelCel(telCel);
        setEndereco(endereco);
        setCidade(cidade);
        setEstado(estado);
        setSenha(senha);
        setNotaProfissional(notaProfissional);
        setTotalNotas(totalNotas);
        setNotaProfissionalAcumulada(notaProfissionalAcumulada);
    }

    //Outras funcoes

    public void atualizarNota(double novaNota){
        setTotalNotas(getTotalNotas()+1);
        setNotaProfissionalAcumulada(getNotaProfissionalAcumulada()+novaNota);
        setNotaProfissional(getNotaProfissionalAcumulada()/getTotalNotas());
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String end) {
        this.endereco = end;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getNotaProfissional() {return notaProfissional;}

    public void setNotaProfissional(double notaProfissional){this.notaProfissional = notaProfissional;}

    public int getTotalNotas(){return totalNotas;}

    public void setTotalNotas(int totalNotas){this.totalNotas = totalNotas;}

    public double getNotaProfissionalAcumulada() {return notaProfissionalAcumulada;}

    public void setNotaProfissionalAcumulada(double notaProfissionalAcumulada){this.notaProfissionalAcumulada = notaProfissionalAcumulada;}
}
