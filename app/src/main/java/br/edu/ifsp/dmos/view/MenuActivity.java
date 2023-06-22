package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.MenuMVP;
import br.edu.ifsp.dmos.presenter.MenuPresenter;

public class MenuActivity extends AppCompatActivity implements MenuMVP.View {

    private TextView perfil;
    private TextView pagInicial;
    private TextView pagServiceCont;
    private TextView pagServiceOfer;
    private TextView pagServiceSol;
    private TextView serviceFav;
    private TextView alterarSenha;
    private TextView sair;
    private MenuPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        presenter = new MenuPresenter(this, this);
        findVById();
        setListener();
    }

    private void setListener() {
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.visualizarPerfil();
            }
        });

        pagInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backToHome();
            }
        });

        pagServiceCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.servicosContratados();
            }
        });

        pagServiceOfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.servicosOferecidos();
            }
        });

        pagServiceSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.servicosSolicitados();
             }
        });

        serviceFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.servicosFavoritos();
            }
        });

        alterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.alterSenha();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               presenter.exist();
            }
        });
    }


    private void findVById() {
        perfil = findViewById(R.id.text_perfil);
        pagInicial = findViewById(R.id.text_pagina_inicial);
        pagServiceCont = findViewById(R.id.text_servicos_contratados);
        pagServiceOfer = findViewById(R.id.text_servicos_oferecidos);
        pagServiceSol = findViewById(R.id.text_servicos_solicitados);
        serviceFav = findViewById(R.id.text_servicos_favoritos);
        alterarSenha = findViewById(R.id.text_Alterar_senha);
        sair = findViewById(R.id.text_sair);
    }

    @Override
    public Context getContext() {
        return this;
    }
}