package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.constants.Constants.FIELD_SENHA;
import static br.edu.ifsp.dmos.constants.Constants.USERS_COLLECTION;

import android.content.Context;

import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifsp.dmos.mvp.UpdatePasswordMVP;
import br.edu.ifsp.dmos.view.md5.Criptografia;

public class UpdatePasswordPresenter implements UpdatePasswordMVP.Presenter {

    private UpdatePasswordMVP.View view;
    private Context context;
    private FirebaseFirestore database;

    private String usuario;


    public UpdatePasswordPresenter(UpdatePasswordMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void updateSenha(String idUsuario, String newSenha) {

        String senhaCrp = Criptografia.criptografar(newSenha);

        database.collection(USERS_COLLECTION)
                .whereEqualTo(FieldPath.documentId(), idUsuario)

                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {

                        String documentId = queryDocumentSnapshots.getDocuments().get(0).getId();

                        database.collection(USERS_COLLECTION).document(documentId)
                                .update(FIELD_SENHA, senhaCrp)
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
