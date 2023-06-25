package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.os.Bundle;

public interface MenuMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{

        void detach();

        void  visualizarPerfil (Bundle bundle);

        void backToHome();

        void servicosContratados();

        void servicosOferecidos(Bundle bundle);

        void servicosSolicitados();

        void servicosFavoritos();

        void alterSenha(String usuario);

        void exist();



    }
}
