package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.os.Bundle;

public interface HomeMVP {

    interface View{
        Context getContext();

    }

    interface Presenter{

        void GoToMenu(Bundle bundle);

        void BuscarTema(String tema, Bundle bundle);
        void search(String nome, Bundle bundle);

    }
}
