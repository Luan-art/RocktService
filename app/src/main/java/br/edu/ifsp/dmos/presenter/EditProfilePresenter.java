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
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.User;
import br.edu.ifsp.dmos.mvp.EditProfileMVP;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.LoginActivity;


public class EditProfilePresenter implements EditProfileMVP.Presenter {

    private EditProfileMVP.View view;
    private Context context;
    private FirebaseFirestore database;


    public EditProfilePresenter(EditProfileMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();
    }
    @Override
    public void updatePerfil(String idUsuario, String nome, String email, String doc, String dataNasc,
                             String usuario, String telCel, String endereco, String cidade, String estado) {

        database.collection(USERS_COLLECTION)
                .whereEqualTo(FieldPath.documentId(), idUsuario)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        String documentId = queryDocumentSnapshots.getDocuments().get(0).getId();


                        boolean isMaiorIdade = isMaiorIdade(dataNasc);

                        CollectionReference listUsuarios = database.collection(USERS_COLLECTION);
                        listUsuarios.whereEqualTo(FIELD_EMAIL, email).get().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                boolean isEmailExists = false;
                                isEmailExists = !task.getResult().isEmpty();

                                if (isEmailExists) {
                                    view.showMessage("Email já está em uso.");
                                } else if (!isMaiorIdade) {
                                    view.showMessage("O usuário não é maior de idade.");
                                } else {
                                    // Realizar a atualização do perfil
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
                                            })
                                            .addOnFailureListener(e -> {
                                                view.showMessage("Erro ao atualizar o perfil: " + e.getMessage());
                                            });
                                }
                            }
                        });
                    } else {
                        view.showMessage("Usuário não encontrado.");
                    }
                })
                .addOnFailureListener(e -> {
                    view.showMessage("Erro ao buscar usuário: " + e.getMessage());
                });
    }






    private boolean isMaiorIdade(String dataNascimento) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNasc = null;
        try {
            dataNasc = new Date(dateFormat.parse(dataNascimento).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dataNasc != null) {
            Calendar calDataNasc = Calendar.getInstance();
            calDataNasc.setTime(dataNasc);

            Calendar calDataAtual = Calendar.getInstance();

            int idadeMinima = 18;

            calDataNasc.add(Calendar.YEAR, idadeMinima);
            return calDataNasc.before(calDataAtual);
        }

        return false;
    }
    }





