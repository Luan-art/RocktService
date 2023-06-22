package br.edu.ifsp.dmos.mvp;

import android.content.Context;
import android.view.MotionEvent;

public interface LoginMVP {

    interface View{
        Context getContext();

        void showEmptyFieldsMessage();

    }

    interface Presenter{

        void Login(String user, String passoword);
        void Cadast();

    }
}
