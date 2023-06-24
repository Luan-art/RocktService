package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.view.MotionEvent;

public interface MenuMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{

        void detach();

        void  visualizarPerfil (String usuario);

        void backToHome();

        void servicosContratados();

        void servicosOferecidos();

        void servicosSolicitados();

        void servicosFavoritos();

        void alterSenha(String usuario);

        void exist();



    }
}
