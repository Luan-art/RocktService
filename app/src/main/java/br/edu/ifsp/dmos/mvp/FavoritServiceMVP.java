package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface FavoritServiceMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();


        void populate(RecyclerView recyclerView, String searchView);

        void deletTesk(String service);

    }
}
