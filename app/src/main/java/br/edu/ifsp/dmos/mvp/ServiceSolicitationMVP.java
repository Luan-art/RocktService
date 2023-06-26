package br.edu.ifsp.dmos.mvp;

import android.content.Context;

public interface ServiceSolicitationMVP {

    interface Presenter{

        void registrarContrato(String idUser, String idService, String dataPrevista, String infoAdd);
    }

    interface View{
        Context getContext();
        void showMessage(String message);
    }
}
