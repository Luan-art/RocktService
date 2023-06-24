package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import java.sql.Date;

public interface UpdatePasswordMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);


    }

    interface Presenter{



        void updateSenha(String usuario, String newSenha);


    }
}
