package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

    private String usuario;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        presenter = new MenuPresenter(this, this);
        findVById();
        setListener();

        bundle = getIntent().getExtras();
    }


    private void setListener() {

        //---------BOTAO PAGINA EDITAR PERFIL----------------------
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.visualizarPerfil(bundle);
            }
        });

        //---------BOTAO PAGINA INICIAL----------------------
        pagInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backToHome(bundle);
            }
        });

        //---------BOTAO PAGINA SERVICOS CONTRATADOS------------
        pagServiceCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.servicosContratados();
            }
        });

        //---------BOTAO PAGINA SERVICOS OFERECIDOS--------------
        pagServiceOfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                presenter.servicosOferecidos(bundle);
            }
        });

        //---------BOTAO PAGINA SERVICOS SOLICITADOS---------------
        pagServiceSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.servicosSolicitados();
             }
        });

        //---------BOTAO PAGINA SERVICOS FAVORITOS-----------------
        serviceFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.servicosFavoritos();
            }
        });

        //---------BOTAO PAGINA ALTERAR SENHA------------------------
        alterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.alterSenha(bundle);
            }
        });

        //---------BOTAO DESLOGAR E RETORNAR A TELA DE LOGIN-----------
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

    //---------RETORNAR A TELA ANTERIOR----------------
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public Context getContext() {
        return this;
    }
}