package br.edu.ifsp.dmos.model.entites;

import java.util.ArrayList;
import java.util.List;

public class Contracts {

    private String idUser;
    private String idService;
    private String dataPrevista;
    private String infoAdd;
    private String motivoCancelamento;
    private boolean favorito;
    private boolean finalizado;
    private double nota;

    private List<Tag> tags;

    private void init(){
        tags = new ArrayList<>();
    }

    public Contracts(){
        // Construtor sem argumentos necessário para a desserialização do Firestore
        init();
    }


    public Contracts(String idUser, String idService, String dataPrevista,String infoAdd, String motivoCancelamento,
                     boolean favorito, boolean finalizado, double nota){

        setIdUser(idUser);
        setIdService(idService);
        setDataPrevista(dataPrevista);
        setInfoAdd(infoAdd);
        setMotivoCancelamento(motivoCancelamento);
        setFavorito(favorito);
        setFinalizado(finalizado);
        setNota(nota);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getInfoAdd() {
        return infoAdd;
    }

    public void setInfoAdd(String infoAdd) {
        this.infoAdd = infoAdd;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
