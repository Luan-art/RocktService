package br.edu.ifsp.dmos.model.entites;

import static br.edu.ifsp.dmos.constants.Constants.FIELD_ADD_INFO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_CATEGORIA;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_CIDADE;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_COMENT;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_DATANASC;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_DATA_SERVICO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_DOC;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_EMAIL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_ENDERECO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_ESTADO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_FORMAS_DE_PAGAMENTO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_FORMA_EXECUCAO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_ID_PROFISSIONAL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_MEDIA_PRECO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME_PROFISSIONAL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME_SERVICO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_TELCEL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_USUARIO;
import static br.edu.ifsp.dmos.constants.Constants.SERVICE_COLLECTION;
import static br.edu.ifsp.dmos.constants.Constants.USERS_COLLECTION;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos.view.HomeActivity;

public class Service {

    private String taskId;
    private String nomeServico;
    private String idProfissional;
    private String nomeProfissional;
    private String categoria;
    private double mediaPreco;
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
                   double mediaPreco, String formasDePagamento, String formaExecucao, String addInfo,
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

    public double getMediaPreco() {
        return mediaPreco;
    }

    public void setMediaPreco(double mediaPreco) {
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


    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------

    public Service retornarService(String idDocService){

        //Tentativa 1
        //-------------------------------------------------------------------------

        Service service = new Service();

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection(SERVICE_COLLECTION)
                .whereEqualTo(FieldPath.documentId(), idDocService)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                service.nomeServico = documentSnapshot.getString(FIELD_NOME_SERVICO);
                                service.idProfissional = documentSnapshot.getString(FIELD_ID_PROFISSIONAL);
                                service.nomeProfissional = documentSnapshot.getString(FIELD_NOME_PROFISSIONAL);
                                service.categoria = documentSnapshot.getString(FIELD_CATEGORIA);
                                service.mediaPreco = documentSnapshot.getDouble(FIELD_MEDIA_PRECO);
                                service.formasDePagamento = documentSnapshot.getString(FIELD_FORMAS_DE_PAGAMENTO);
                                service.formaExecucao = documentSnapshot.getString(FIELD_FORMA_EXECUCAO);
                                service.addInfo = documentSnapshot.getString(FIELD_ADD_INFO);
                                service.coment = documentSnapshot.getString(FIELD_COMENT);

                                Log.d("Service Return Banco", "Value: Encontrou o equivalente no banco");
                                Log.d("Service Return Banco", "Value: Valor do nome servico " + (service.nomeServico));
                            }
                        }
                    }
                });

        Log.d("Service Return Banco", "Value: Valor do nome servico antes do return " + (service.nomeServico));
        return service;
    }


}
