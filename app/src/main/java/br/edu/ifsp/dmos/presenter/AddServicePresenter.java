package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.model.entites.User;
import br.edu.ifsp.dmos.mvp.AddServiceMVP;
import br.edu.ifsp.dmos.mvp.SignUpMVP;
import br.edu.ifsp.dmos.view.LoginActivity;

public class AddServicePresenter implements AddServiceMVP.Presenter {


    private AddServiceMVP.View view;
    private Context context;
    private FirebaseFirestore database;
    private String firestoreId = "";

    public AddServicePresenter(AddServiceMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();
    }


    @Override
    public void CadastrarTarefa(String nomeServico, String nomeProfissional, String categoria, boolean mediaPreco,
                                String formasDePagamento, String formaExecucao, String addInfo, double nota,
                                String coment, Date date, String status) {
        CollectionReference listaServicos = database.collection(Constants.SERVICE_COLLECTION);

        Service servico = new Service(nomeServico, nomeProfissional, categoria, mediaPreco,
                formasDePagamento, formaExecucao, addInfo, nota, coment, date, status);

           listaServicos.add(servico).addOnSuccessListener(documentReference -> {
               Toast.makeText(view.getContext(), "Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(e -> {
                     Toast.makeText(view.getContext(), "Erro ao cadastrar tarefa.", Toast.LENGTH_SHORT).show();
                    });

    }


    @Override
    public boolean isNewUser() {
        return  firestoreId.isEmpty();
    }


}
