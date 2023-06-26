package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Contracts;
import br.edu.ifsp.dmos.mvp.ServiceSolicitationMVP;
import br.edu.ifsp.dmos.view.LoginActivity;

public class ServiceSolicitationPresenter implements ServiceSolicitationMVP.Presenter {

    private Context context;
    private ServiceSolicitationMVP.View view;
    private FirebaseFirestore database;


    public ServiceSolicitationPresenter(ServiceSolicitationMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();
    }

    //---------CRIACAO DE NOVO DOCUMENTO CONTRATO NO FIREBASE--------------------------------------------------------------
    @Override
    public void registrarContrato(String idUser, String idService, String dataPrevista, String infoAdd) {


        CollectionReference dbContratos = database.collection(Constants.CONTRACTS_COLLECTION);

        Contracts contratoNovo = new Contracts(idUser, idService, dataPrevista, infoAdd, "", false, false, 0.0);

        dbContratos.add(contratoNovo).addOnSuccessListener(documentReference -> {
            Toast.makeText(view.getContext(), "Contrato cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(view.getContext(), "Erro ao cadastrar contrato.", Toast.LENGTH_SHORT).show();
        });

    }
}
