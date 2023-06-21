package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;

import br.edu.ifsp.dmos.mvp.MainMVP;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.MainActivity;

public class MainPresenter implements MainMVP.Presenter{
    private Context context;
    private MainActivity mainActivity;

    public MainPresenter(MainMVP.View view){
        this.context = context;
        this.mainActivity = mainActivity;
    }
    @Override
    public void toch(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            // Crie um Intent para redirecionar para a outra Activity
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }

    }
}
