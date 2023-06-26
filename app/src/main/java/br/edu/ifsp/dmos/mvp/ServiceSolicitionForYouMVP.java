package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface ServiceSolicitionForYouMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void detach();

        void populate(RecyclerView recyclerView, String searchView);


    }
}
