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

    public ServiceOfferedPresenter(ServiceOfferedMVP.View serviceOfferedActivity, Context context) {
        this.view = serviceOfferedActivity;
        data = FirebaseFirestore.getInstance();
        this.context = context;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void populate(RecyclerView recyclerView, String searchView) {
        Query query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        if (searchView != null){
            if (searchView.length() == 0){
                populate(recyclerView, null);
            } else{
                query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO).startAt(searchView).endAt(searchView + '\uf8ff');
            }
        }

        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>().setQuery(query, Service.class).build();

        adapter = new ServiceOfferedAdapter(options, view,context);

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
        Intent intent = new Intent(context, EditServiceActivity.class);
        intent.putExtra("chave", chave);
        context.startActivity(intent);
    }

    @Override
    public void delet(String idService) {

        DocumentReference documentRef = data.collection(SERVICE_COLLECTION).document(idService);
        documentRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(context, "Serviço excluído com sucesso", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Falha ao excluir o serviço", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    //---------NAVEGAR PARA A TELA ADICIONAR SERVICO-------------------------------------
    @Override
    public void goToAddService(Bundle bundle) {
        Intent intent = new Intent(context, AddServiceActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
