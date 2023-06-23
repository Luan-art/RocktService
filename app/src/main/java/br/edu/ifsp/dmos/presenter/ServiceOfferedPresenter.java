package br.edu.ifsp.dmos.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ServiceOfferedMVP;
import br.edu.ifsp.dmos.view.AddServiceActivity;
import br.edu.ifsp.dmos.view.ItemClickListener;
import br.edu.ifsp.dmos.view.adapter.ServiceOfferedAdapter;

public class ServiceOfferedPresenter implements ServiceOfferedMVP.Presenter {

    private ServiceOfferedMVP.View view;
    private FirebaseFirestore data;
    private ServiceOfferedAdapter adapter;
    public ServiceOfferedPresenter(ServiceOfferedMVP.View serviceOfferedActivity) {
        this.view = serviceOfferedActivity;
        data = FirebaseFirestore.getInstance();
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void populate(RecyclerView recyclerView) {
        Query query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>().setQuery(query, Service.class).build();

        adapter = new ServiceOfferedAdapter(options);
        adapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(String referenceId) {
                addService(referenceId);
            }
        });
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

    private void addService(String documento) {
        Intent intent = new Intent(view.getContext(), AddServiceActivity.class);
        intent.putExtra(Constants.FIRESOTRE_DOCUMENT_KEY, documento);
        view.getContext().startActivity(intent);
    }
}
