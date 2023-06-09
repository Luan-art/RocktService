package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmos.model.entites.Service;

public interface HiredServiceMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void detach();

        void rateService(String service);

        void populate(RecyclerView recyclerView, String searchView);

        void deleteService(Service service);

    }
}
