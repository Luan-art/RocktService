package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmos.model.entites.Service;

public interface FavoritServiceMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();


        void populate(RecyclerView recyclerView);

        void deletTesk(Service service);

    }
}
