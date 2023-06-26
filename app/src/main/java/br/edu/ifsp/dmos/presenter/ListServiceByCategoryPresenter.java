package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME_SERVICO;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ListServiceByCategoryMVP;
import br.edu.ifsp.dmos.view.adapter.FavoritServiceAdapter;
import br.edu.ifsp.dmos.view.adapter.ListServiceRecyclerAdapter;

public class ListServiceByCategoryPresenter implements ListServiceByCategoryMVP.Presenter {

    private ListServiceByCategoryMVP.View view;
    private FirebaseFirestore database;
    private ListServiceRecyclerAdapter adapter;
    private Context context;


    public ListServiceByCategoryPresenter(ListServiceByCategoryMVP.View view, Context context){
        this.view = view;
        database = FirebaseFirestore.getInstance();
        this.context = context;
    }

    @Override
    public void detach() {
        this.view = null;
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

    //---------POPULAR LISTA DE SERVICOS BASEANDO-SE EM TEMA------------------------------
    @Override
    public void populateByCategoria(RecyclerView mRecyclerView, String tema) {
        Query query = database.collection(Constants.SERVICE_COLLECTION).whereEqualTo("categoria", tema)
                .orderBy(FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>().setQuery(query, Service.class).build();

        adapter = new ListServiceRecyclerAdapter(options, view, view.getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    //---------POPULAR LISTA DE SERVICOS BASEANDO-SE EM NOME---------------------------------
    @Override
    public void populateByName(RecyclerView mRecyclerView, String nome) {
        Query query = database.collection(Constants.SERVICE_COLLECTION).whereEqualTo(FIELD_NOME_SERVICO, nome).
                orderBy(FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>().setQuery(query, Service.class).build();
        Log.d("chegueiPresenter", nome);

        adapter = new ListServiceRecyclerAdapter(options, view, view.getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void populate(RecyclerView mRecyclerView, String search) {
        Query query = database.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        if (search != null){
            if (search.length() == 0){
                populate(mRecyclerView, null);
            } else{
                query = database.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO).startAt(search).endAt(search + '\uf8ff');
            }
        }
        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>()
                .setQuery(query, Service.class).build();

        adapter =  new ListServiceRecyclerAdapter(options, view, view.getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(adapter);
    }
}
