package br.edu.ifsp.dmos.mvp;

import android.content.Context;

import java.sql.Date;

public interface SignUpMVP {

    interface View{
        Context getContext();


        void showEmptyFieldsMessage();
    }

    interface Presenter{

        void Enter();
        void RealizarCadastro(String nome, String email, String doc, Date dataNasci, String usuario,
                          String senha, String conSenha, String telCel);

        boolean isNewUser();
        

    }
}
