package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.constants.Constants.USERS_COLLECTION;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifsp.dmos.mvp.SignUpMVP;
import br.edu.ifsp.dmos.mvp.UpdatePasswordMVP;

public class UpdatePasswordPresenter implements UpdatePasswordMVP.Presenter {

    private UpdatePasswordMVP.View view;
    private Context context;
    private FirebaseFirestore database;

    private String usuario;


    public UpdatePasswordPresenter(UpdatePasswordMVP.View view, Context context, String usuario) {
        this.view = view;
        this.context = context;
        this.usuario = usuario;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void updateSenha(String usuario, String newSenha) {

        database.collection(USERS_COLLECTION)
                .whereEqualTo("usuario", usuario)

                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {

                        String documentId = queryDocumentSnapshots.getDocuments().get(0).getId();

                        database.collection(USERS_COLLECTION).document(documentId)
                                .update("senha", newSenha)
                                .addOnSuccessListener(aVoid -> {
                                    view.showMessage("Senha atualizada com sucesso.");
                                })
                                .addOnFailureListener(e -> {
                                    view.showMessage("Erro ao atualizar a senha: " + e.getMessage());
                                });
                    } else {
                        view.showMessage("Usuário não encontrado.");
                    }
                })
                .addOnFailureListener(e -> {
                    view.showMessage("Erro ao buscar usuário: " + e.getMessage());
                });
    }

}
