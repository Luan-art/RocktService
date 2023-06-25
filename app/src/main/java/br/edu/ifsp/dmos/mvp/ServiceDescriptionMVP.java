package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmos.model.entites.Service;

public interface ServiceDescriptionMVP {

    interface View{
        Context getContext();

        void preencher();


    }

    interface Presenter{

        void deatach();

        void contratarTarefa(Service service);

    }

    interface OnServiceRetrievedListener {
        Service onServiceRetrieved(Service service);
        void onServiceRetrievalFailed(String errorMessage);
    }
}
