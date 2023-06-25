package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface ListServiceByCategoryMVP {

    interface View {

        Context getContext();

    }

    interface Presenter {

        void detach();

        void startListener();

        void stopListener();

        void populateByCategoria(RecyclerView mRecyclerView, String tema);

        void populateByName(RecyclerView mRecyclerView, String nome);
    }

    interface Adapter {

        void descricaoServico (Context context, String taskId);
    }
}
