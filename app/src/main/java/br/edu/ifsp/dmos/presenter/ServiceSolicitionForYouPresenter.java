package br.edu.ifsp.dmos.presenter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.HiredServiceMVP;
import br.edu.ifsp.dmos.mvp.ServiceSolicitionForYouMVP;
import br.edu.ifsp.dmos.view.adapter.HiredServiceAdapter;
import br.edu.ifsp.dmos.view.adapter.ServiceSolicitionForYouAdapter;

public class ServiceSolicitionForYouPresenter implements ServiceSolicitionForYouMVP.Presenter {

    private ServiceSolicitionForYouMVP.View view;
    private FirebaseFirestore data;
    private ServiceSolicitionForYouAdapter adapter;

    private Context context;

    public ServiceSolicitionForYouPresenter(ServiceSolicitionForYouMVP.View view, Context context) {
        this.view = view;
        data = FirebaseFirestore.getInstance();
        this.context = context;
    }
    @Override
    public void detach() {
        view = null;

    }

    @Override
    public void populate(RecyclerView recyclerView) {


        Query query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO,
                Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>().
                setQuery(query, Service.class).build();

        adapter = new ServiceSolicitionForYouAdapter(options, view,context);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

    }


    public void startListener() {
        if (adapter != null)
            adapter.startListening();
    }


    public void stopListener() {
        if (adapter != null)
            adapter.stopListening();
    }

}
