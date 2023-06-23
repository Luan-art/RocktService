package br.edu.ifsp.dmos.presenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ListServiceByCategoryMVP;
import br.edu.ifsp.dmos.view.adapter.ListServiceRecyclerAdapter;

public class ListServiceByCategoryPresenter implements ListServiceByCategoryMVP.Presenter {

    private ListServiceByCategoryMVP.View view;
    private FirebaseFirestore database;
    private ListServiceRecyclerAdapter adapter;

    public ListServiceByCategoryPresenter(ListServiceByCategoryMVP.View view){
        this.view = view;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void detach() {
        this.view = null;
    }

    public void populate(RecyclerView recyclerView) {
        Query query = database.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>().setQuery(query, Service.class).build();

        adapter = new ListServiceRecyclerAdapter(options);
        /*adapter.setClickListener(new ItemClicklListener() {
            @Override
            public void onClick(String referenceId) {
                abrirDetalhes(referenceId);
            }
        });
        */
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
