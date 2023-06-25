package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.constants.Constants.FIELD_CIDADE;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_DATANASC;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_DOC;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_EMAIL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_ENDERECO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_ESTADO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_TELCEL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_USUARIO;
import static br.edu.ifsp.dmos.constants.Constants.USERS_COLLECTION;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifsp.dmos.mvp.EditProfilePresenterMVP;
import br.edu.ifsp.dmos.mvp.UpdatePasswordMVP;
import br.edu.ifsp.dmos.view.HomeActivity;


public class EditProfilePresenter implements EditProfilePresenterMVP.Presenter {

    private EditProfilePresenterMVP.View view;
    private Context context;
    private FirebaseFirestore database;


    public EditProfilePresenter(EditProfilePresenterMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();
    }
    @Override
    public void updatePerfil(String idUsuario, String nome,String email, String doc,  String dataNasc,
                             String usuario, String telCel, String endereco,String cidade, String estado) {

        database.collection(USERS_COLLECTION)
                .whereEqualTo(FieldPath.documentId(), idUsuario)

                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {

                        String documentId = queryDocumentSnapshots.getDocuments().get(0).getId();

                        database.collection(USERS_COLLECTION).document(documentId)
                                .update(FIELD_NOME, nome,
                                        FIELD_EMAIL, email,
                                        FIELD_DOC, doc,
                                        FIELD_DATANASC, dataNasc,
                                        FIELD_USUARIO, usuario,
                                        FIELD_TELCEL, telCel,
                                        FIELD_ENDERECO, endereco,
                                        FIELD_CIDADE, cidade,
                                        FIELD_ESTADO, estado)
                                .addOnSuccessListener(aVoid -> {
                                    view.showMessage("Perfil atualizada com sucesso.");

                                    retornoHome();
                                })
                                .addOnFailureListener(e -> {
                                    view.showMessage("Erro ao atualizar a Perfil: " + e.getMessage());
                                });
                    } else {
                        view.showMessage("Usuário não encontrado.");
                    }
                })
                .addOnFailureListener(e -> {
                    view.showMessage("Erro ao buscar usuário: " + e.getMessage());
                });
    }

        public void retornoHome(){
            Bundle bundle = new Bundle();

            // Iniciar a HomeActivity e passar a Bundle
            Intent intent = new Intent(context, HomeActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }



