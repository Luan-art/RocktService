package br.edu.ifsp.dmos.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.FavoritServiceMVP;
import br.edu.ifsp.dmos.mvp.HiredServiceMVP;
import br.edu.ifsp.dmos.view.FavoritedServiceActivity;
import br.edu.ifsp.dmos.view.adapter.FavoritServiceAdapter;
import br.edu.ifsp.dmos.view.adapter.HiredServiceAdapter;

public class FavoritedServicePresenter implements FavoritServiceMVP.Presenter {

    private FavoritServiceMVP.View view;
    private FirebaseFirestore data;
    private FavoritServiceAdapter adapter;

    private Context context;

    public FavoritedServicePresenter(FavoritServiceMVP.View view, Context context) {

        this.view = view;
        data = FirebaseFirestore.getInstance();
        this.context = context;
    }

    @Override
    public void deatach() {
        view = null;
    }


    @Override
    public void deletTesk(Service service) {

    }


    public void populate(RecyclerView mRecyclerView) {

        Query query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Service> options = new FirestoreRecyclerOptions.Builder<Service>()
                .setQuery(query, Service.class).build();

        adapter = new FavoritServiceAdapter(options, view,context);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(adapter);
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
