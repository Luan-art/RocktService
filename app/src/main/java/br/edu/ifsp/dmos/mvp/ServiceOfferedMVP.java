package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

public interface ServiceOfferedMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void detach();

        void populate(RecyclerView recyclerView, String searchView);

        void startListener();

        void stopListener();

        void edit(String service);

        void delet(String service);

        void goToAddService(Bundle bundle);
    }
}
