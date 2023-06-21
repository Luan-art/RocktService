package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.MainMVP;
import br.edu.ifsp.dmos.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainMVP.View, View.OnTouchListener {

    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        presenter.toch(motionEvent);
        return true;
    }

    @Override
    public Context getContext() {
        return null;
    }
}