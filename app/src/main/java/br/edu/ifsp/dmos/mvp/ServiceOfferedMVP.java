package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmos.model.entites.Service;

public interface ServiceOfferedMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void detach();

        void populate(RecyclerView recyclerView);

        void startListener();

        void stopListener();

        void edit(Service service);

        void delet(Service service);

        void goToAddService(Bundle bundle);
    }
}
