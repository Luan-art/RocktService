package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;

import br.edu.ifsp.dmos.mvp.MainMVP;
import br.edu.ifsp.dmos.view.LoginActivity;

public class MainPresenter implements MainMVP.Presenter {
    private Context context;
    private MainMVP.View view;

    public MainPresenter(MainMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void detach() {
        this.view = null;
    }


    //-----FUNÇÃO PARA IR AO LOGIN ASSIM QUE DER UM CLIQUE NA TELA--------------
    //--------------------------------------------------------------------------
    @Override
    public void toch(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            // Crie um Intent para redirecionar para a outra Activity
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }
}
