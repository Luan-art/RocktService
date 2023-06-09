package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.constants.Constants.FIELD_ADD_INFO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_CATEGORIA;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_FORMAS_DE_PAGAMENTO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_FORMA_EXECUCAO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_MEDIA_PRECO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME_SERVICO;
import static br.edu.ifsp.dmos.constants.Constants.SERVICE_COLLECTION;

import android.content.Context;

import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifsp.dmos.mvp.EditServiceMVP;

public class EditServicePresenter implements EditServiceMVP.Presenter{

    private EditServiceMVP.View view;
    private Context context;
    private FirebaseFirestore database;

    public EditServicePresenter(EditServiceMVP.View view, Context context) {

        this.view= view;
        this.context = context;
        database = FirebaseFirestore.getInstance();

    }

    //---------UPDATE DO DOCUMENTO SERVICO NO FIREBASE----------------------------------------------------------------------
    @Override
    public void updateService(String nomeServico, String categoria, String mediaPreco, String formaPagamento,
                              String execucao, String InfoAdd, String chave) {

        // Convertendo mediaPreco para um tipo numérico
        double preco = Double.parseDouble(mediaPreco);

        database.collection(SERVICE_COLLECTION)
                .whereEqualTo(FieldPath.documentId(), chave)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {

                        String documentId = queryDocumentSnapshots.getDocuments().get(0).getId();

                        database.collection(SERVICE_COLLECTION).document(documentId)
                                .update(FIELD_NOME_SERVICO, nomeServico,
                                        FIELD_CATEGORIA, categoria,
                                        FIELD_MEDIA_PRECO, preco, // Usando o valor convertido
                                        FIELD_FORMAS_DE_PAGAMENTO, formaPagamento,
                                        FIELD_FORMA_EXECUCAO, execucao,
                                        FIELD_ADD_INFO, InfoAdd)
                                .addOnSuccessListener(aVoid -> {
                                    view.showMessage("Perfil atualizado com sucesso.");
                                })
                                .addOnFailureListener(e -> {
                                    view.showMessage("Erro ao atualizar o perfil: " + e.getMessage());
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
