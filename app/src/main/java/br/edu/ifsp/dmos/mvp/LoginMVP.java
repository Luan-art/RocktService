package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.view.MotionEvent;

public interface LoginMVP {

    interface View{
        Context getContext();

        void showErrorMessage(String erro_ao_buscar_usuário);
    }

    interface Presenter{

        void login(String user, String passoword);
        void cadast();

    }
}
