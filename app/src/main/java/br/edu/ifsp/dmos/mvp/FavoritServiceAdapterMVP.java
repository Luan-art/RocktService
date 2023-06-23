package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmos.model.entites.Service;

public interface FavoritServiceAdapterMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();

        void openDetails(Service service);

        void populateList(RecyclerView recyclerView);

        void deletTesk(Service service);

        void updateList();
    }
}
