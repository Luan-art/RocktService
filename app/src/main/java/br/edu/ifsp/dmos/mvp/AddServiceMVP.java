package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import java.sql.Date;

public interface AddServiceMVP {
    interface View{
        Context getContext();

    }

    interface Presenter {


        void CadastrarTarefa(String nomeServico, String nomeProfissional, String categoria, boolean mediaPreco,
                                    String formasDePagamento, String formaExecucao, String addInfo, double nota, String coment);

        boolean isNewUser();
    }
}
