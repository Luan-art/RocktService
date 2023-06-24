package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface ListServiceByCategoryMVP {

    interface View {

        Context getContext();

    }

    interface Presenter {

        void detach();

        void populate(RecyclerView recyclerView);

        void startListener();

        void stopListener();

    }

    interface Adapter {

        void descricaoServico (Context context);
    }
}
