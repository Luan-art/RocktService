package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
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
import br.edu.ifsp.dmos.view.LoginActivity;

public class AddServicePresenter implements AddServiceMVP.Presenter {

    private AddServiceMVP.View view;
    private Context context;
    private FirebaseFirestore database;
    private String firestoreId = "";

    private String usuario;
    private String nomeProfissional;

    public AddServicePresenter(AddServiceMVP.View view, Context context, String idUsuarioBundle) {
        this.view = view;
        this.context = context;
        this.usuario = idUsuarioBundle;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void CadastrarTarefa(String nomeServico, String idProfissional, String nomeProfissional, String categoria, double precoHora,
                                String formasDePagamento, String formaExecucao, String addInfo,
                                String coment, Date date, String status) {

        //---------MULTIPLOS LOGS UTILIZADOS PARA GARANTIR QUE OS DADOS ESTAO DEVIDAMENTE ESTRUTURADOS----------------------------------
        //------------------------------------------------------------------------------------------------------------------------------
        //--------eu pensei em apagar mas achei um bloco bonito e deixo como honra as horas gastas nesse código-------------------------
        //------------------------------------------------------------------------------------------------------------------------------
        Log.d("Nome Servico chegando", "Value: " + (nomeServico));
        Log.d("id Profissional chegando", "Value: " + (idProfissional));
        Log.d("nome Profissional chegando", "Value: " + (nomeProfissional));
        Log.d("categoria chegando", "Value: " + (categoria));
        Log.d("preco hora chegando", "Value: " + (precoHora));
        Log.d("forma pagamento chegando", "Value: " + (formasDePagamento));
        Log.d("forma execucao chegando", "Value: " + (formaExecucao));
        Log.d("add info chegando", "Value: " + (addInfo));
        Log.d("coment chegando", "Value: " + (coment));
        Log.d("date", "Value: " + (date));
        Log.d("status", "Value: " + (status));


        //---------CADASTRO DO SERVICO NO FIREBASE------------------------------------------------------------------
        CollectionReference listaServicos = database.collection(Constants.SERVICE_COLLECTION);


        //originalmente iria haver uma busca pelos dados do usuario no banco, mas a nao cooperacao do firebase tornou
        // mais viavel pegar os dados via bundle
        //--------------------------------------------------------------------------------------------------------------------
        Log.d("nome Profissional chegando", "Value: " + (nomeProfissional));
        Service servico = new Service(nomeServico, idProfissional, nomeProfissional, categoria, precoHora,
                formasDePagamento, formaExecucao, addInfo, coment, date, status);

        listaServicos.add(servico)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(view.getContext(), "Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(view.getContext(), "Erro ao cadastrar tarefa.", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public boolean isNewUser() {
        return firestoreId.isEmpty();
    }

    private void setNomeProfissional(String nome) {
        nomeProfissional = nome;
    }
}
