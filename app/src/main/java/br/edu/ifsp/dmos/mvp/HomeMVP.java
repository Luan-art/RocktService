package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.os.Bundle;

public interface HomeMVP {

    interface View{
        Context getContext();

    }

    interface Presenter{

        void GoToMenu(Bundle bundle);

        void BuscarTema(String tema);
        void search(String nome);

    }
}
