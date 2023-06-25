package br.edu.ifsp.dmos.model.entites;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private String taskId;
    private String nomeServico;
    private String idProfissional;
    private String nomeProfissional;
    private String categoria;
    private boolean mediaPreco;
    private String formasDePagamento;
    private String formaExecucao;
    private String addInfo;
    private String status;
    private Date date;
    private String coment;
    private List<Tag> tags;

    private void init(){
        tags = new ArrayList<>();
    }

    public Service() {
        // Construtor sem argumentos necessário para a desserialização do Firestore
        init();
    }

    public Service(String nomeServico, String idProfissional, String nomeProfissional, String categoria,
                   boolean mediaPreco, String formasDePagamento, String formaExecucao, String addInfo,
                   String coment, Date date, String status) {

        setNomeServico(nomeServico);
        setIdProfissional(idProfissional);
        setNomeProfissional(nomeProfissional);
        setCategoria(categoria);
        setMediaPreco(mediaPreco);
        setFormasDePagamento(formasDePagamento);
        setFormaExecucao(formaExecucao);
        setAddInfo(addInfo);
        setComent(coment);
        setDate(date);
        setStatus(status);
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(String idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isMediaPreco() {
        return mediaPreco;
    }

    public void setMediaPreco(boolean mediaPreco) {
        this.mediaPreco = mediaPreco;
    }

    public String getFormasDePagamento() {
        return formasDePagamento;
    }

    public void setFormasDePagamento(String formasDePagamento) {
        this.formasDePagamento = formasDePagamento;
    }

    public String getFormaExecucao() {
        return formaExecucao;
    }

    public void setFormaExecucao(String formaExecucao) {
        this.formaExecucao = formaExecucao;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
