package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.constants.Constants.SERVICE_COLLECTION;

import android.content.Context;
import android.content.Intent;
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
import br.edu.ifsp.dmos.mvp.FavoritServiceMVP;
import br.edu.ifsp.dmos.view.FavoritedServiceActivity;
import br.edu.ifsp.dmos.view.adapter.FavoritServiceAdapter;

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
    public void deletTesk(String idService) {

        DocumentReference documentRef = data.collection(SERVICE_COLLECTION).document(idService);
        documentRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(context, "Serviço excluído com sucesso", Toast.LENGTH_SHORT).show();

                        // Recarregue a Activity FavorityServiceActivity
                        Intent intent = new Intent(context, FavoritedServiceActivity.class);
                        context.startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Falha ao excluir o serviço", Toast.LENGTH_SHORT).show();

                    }
                });


    }


    public void populate(RecyclerView mRecyclerView, String search) {

        Query query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
        if (search != null){
            if (search.length() == 0){
                populate(mRecyclerView, null);
            } else{
                query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO).startAt(search).endAt(search + '\uf8ff');
            }
        }
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
