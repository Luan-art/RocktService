package br.edu.ifsp.dmos.model.entites;

public class Service {

    private String nomeServico;
    private String nomeProfissional;
    private String categoria;
    private boolean mediaPreco;
    private String formasDePagamento;
    private String formaExecucao;
    private String addInfo;
    private double nota;
    private String coment;

    public Service(String nomeServico, String nomeProfissional, String categoria, boolean mediaPreco,
                   String formasDePagamento, String formaExecucao, String addInfo, double nota, String coment) {

        setNomeServico(nomeServico);
        setNomeProfissional(nomeProfissional);
        setCategoria(categoria);
        setMediaPreco(mediaPreco);
        setFormasDePagamento(formasDePagamento);
        setFormaExecucao(formaExecucao);
        setAddInfo(addInfo);
        setNota(nota);
        setComent(coment);
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
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

    public void setFormaExecucao(String fomraExecucao) {
        this.formaExecucao = fomraExecucao;
    }
    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}
