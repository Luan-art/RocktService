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

        //Service findService();

        //User findUser();

        void deatach();

        void contratarTarefa(Service service);

    }
}
