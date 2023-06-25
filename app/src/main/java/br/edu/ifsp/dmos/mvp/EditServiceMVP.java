package br.edu.ifsp.dmos.mvp;

import android.content.Context;

public interface EditServiceMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);


    }

    interface Presenter{



        void updateService(String nomeServico, String categoria,String mediaPreco, String formaPagamento,
                           String execucao, String InfoAdd);


    }
}
