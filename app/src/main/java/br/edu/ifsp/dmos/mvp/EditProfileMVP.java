package br.edu.ifsp.dmos.mvp;

import android.content.Context;

public interface EditProfileMVP {
    interface View{
        Context getContext();

        void showMessage(String mensage);


    }

    interface Presenter{



        void updatePerfil(String usuarioOld, String nome,String email, String doc,  String dataNasc,
        String usuario, String telCel, String endereco,String cidade, String estado);


    }
}
