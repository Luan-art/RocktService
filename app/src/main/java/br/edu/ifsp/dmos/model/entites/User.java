package br.edu.ifsp.dmos.model.entites;

import java.sql.Date;

public class User {

    private String nome;
    private String email;
    private String doc;
    private Date datanasc;
    private String usuario;
    private String telCel;
    private String endereco;
    private String cidade;
    private String estado;
    private String senha;

    public User(String nome, String email, String doc,Date datanasc,String usuario, String telCel,
                String endereco,String cidade, String estado, String senha) {

        setNome(nome);
        setEmail(email);
        setDoc(doc);
        setDatanasc(datanasc);
        setUsuario(usuario);
        setTelCel(telCel);
        setEndereco(endereco);
        setCidade(cidade);
        setEstado(estado);
        setSenha(senha);
    }

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

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
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
}
