package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.constants.Constants.SERVICE_COLLECTION;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ServiceOfferedMVP;
import br.edu.ifsp.dmos.view.AddServiceActivity;
import br.edu.ifsp.dmos.view.EditServiceActivity;
import br.edu.ifsp.dmos.view.FavoritedServiceActivity;
import br.edu.ifsp.dmos.view.ServiceOfferedActivity;
import br.edu.ifsp.dmos.view.adapter.ServiceOfferedAdapter;

public class ServiceOfferedPresenter implements ServiceOfferedMVP.Presenter {

    private ServiceOfferedMVP.View view;
    private FirebaseFirestore data;
    private ServiceOfferedAdapter adapter;

    private Context context;

    //construtor
    public ServiceOfferedPresenter(ServiceOfferedMVP.View serviceOfferedActivity, Context context) {
        this.view = serviceOfferedActivity;
        data = FirebaseFirestore.getInstance();
        this.context = context;
    }

    @Override
    public void detach() {
        view = null;
    }

    // populando adapater
    @Override
    public void populate(RecyclerView recyclerView, String searchView) {
        Query query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        //verificar se texto de pesquisa existe ou seja diferente de nulo e maior que 0
        if (searchView != null){
            if (searchView.length() == 0){
                //caso não seja nulo mas seja igual a zero ocorre chamada recursiva setando searchView como nulo
                populate(recyclerView, null);
            } else{
                //caso não seja nulo e maior que zero query receber os valores do banco de dados filtrados
                query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO).startAt(searchView).endAt(searchView + '\uf8ff');
            }
        }

        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>().setQuery(query, Service.class).build();

        //chama adapter
        adapter = new ServiceOfferedAdapter(options, view,context);

        //set o adpater dentro da recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void startListener() {
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public void stopListener() {
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    public void edit(String chave) {
        //Invoca a EditServiceActivity e envia para ela a String chave contendo o ID do service clicado pelo usuario

        Intent intent = new Intent(context, EditServiceActivity.class);
        intent.putExtra("chave", chave);
        context.startActivity(intent);
    }

    @Override
    public void delet(String chave) {
        //busca no banco de dados service_collection algum documento com a chave passada (id) caso encotre o deleta
        DocumentReference documentRef = data.collection(SERVICE_COLLECTION).document(chave);
        documentRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //mensagem se conseguir deletar
                        Toast.makeText(context, "Serviço excluído com sucesso", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //mensagem se falhar ao deletar

                        Toast.makeText(context, "Falha ao excluir o serviço", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    //---------NAVEGAR PARA A TELA ADICIONAR SERVICO-------------------------------------
    @Override
    public void goToAddService(Bundle bundle) {

        //Invoca o AddServiceActivity

        Intent intent = new Intent(context, AddServiceActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
