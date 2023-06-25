package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ServiceOfferedMVP;
import br.edu.ifsp.dmos.view.AddServiceActivity;
import br.edu.ifsp.dmos.view.EditProfileActivity;
import br.edu.ifsp.dmos.view.EditServiceActivity;
import br.edu.ifsp.dmos.view.HiredServiceActivity;
import br.edu.ifsp.dmos.view.ItemClickListener;
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
    public void populate(RecyclerView recyclerView) {
        Query query = data.collection(Constants.SERVICE_COLLECTION).orderBy(Constants.FIELD_NOME_SERVICO, Query.Direction.ASCENDING);
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
    public void edit(Service service) {
        Intent intent = new Intent(context, EditServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void delet(Service service) {
        if (service != null) {

             data.collection(Constants.SERVICE_COLLECTION)
                    .document()
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } else{
            Log.d("nome Do Usuario no menu activity", "Value: " + (service));
        }
    }

    @Override
    public void goToAddService(Bundle bundle) {
        Intent intent = new Intent(context, AddServiceActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
