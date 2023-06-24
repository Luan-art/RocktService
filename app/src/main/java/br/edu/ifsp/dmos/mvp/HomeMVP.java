package br.edu.ifsp.dmos.mvp;

import android.content.Context;

public interface HomeMVP {

    interface View{
        Context getContext();

    }

    interface Presenter{

        void GoToMenu(String usuario);
        void BuscarTema(String tema);
        void search(String nome);

    }
}
