package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Date;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.model.entites.User;
import br.edu.ifsp.dmos.mvp.AddServiceMVP;
import br.edu.ifsp.dmos.mvp.SignUpMVP;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.LoginActivity;

public class AddServicePresenter implements AddServiceMVP.Presenter {


    private AddServiceMVP.View view;
    private Context context;
    private FirebaseFirestore database;
    private String firestoreId = "";

    private String usuario;

    public AddServicePresenter(AddServiceMVP.View view, Context context, String idUsuarioBundle) {
        this.view = view;
        this.context = context;
        this.usuario = idUsuarioBundle;
        database = FirebaseFirestore.getInstance();
    }


    @Override
    public void CadastrarTarefa(String nomeServico, String idProfissional, String nomeProfissional, String categoria, boolean precoHora,
                                String formasDePagamento, String formaExecucao, String addInfo,
                                String coment, Date date, String status) {
        CollectionReference listaServicos = database.collection(Constants.SERVICE_COLLECTION);

        CollectionReference listUsuarios = database.collection(Constants.USERS_COLLECTION);

        listUsuarios.whereEqualTo().get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    QuerySnapshot querySnapshot = task.getResult();
                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                        String stringNomeGenerico = documentSnapshot.getString("");
            }
        });
            Service servico = new Service(nomeServico, idProfissional, nomeProfissional, categoria, precoHora,
                formasDePagamento, formaExecucao, addInfo, coment, date, status);

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
