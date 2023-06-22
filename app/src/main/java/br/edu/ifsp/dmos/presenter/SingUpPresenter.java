package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;

import java.sql.Date;

import br.edu.ifsp.dmos.mvp.SignUpMVP;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.SingUpActivity;

public class SingUpPresenter implements SignUpMVP.Presenter {

    private SignUpMVP.View view;
    private  Context context;

    public SingUpPresenter(SignUpMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void Enter() {
        Intent intent = new Intent(context, LoginPresenter.class);
        context.startActivity(intent);
    }

    @Override
    public void RealizarCadastro(String nome, String email, String doc, Date dataNasci,
                                 String usuario, String telCel,  String senha, String confSenha) {

        if (nome.isEmpty() || email.isEmpty() || doc.isEmpty() || //dataNasci == null *~/||
                usuario.isEmpty() || telCel.isEmpty() || senha.isEmpty() || confSenha.isEmpty()) {
            view.showEmptyFieldsMessage();
        } else {
            //RealizarCadastro(nome, email, doc, dataNasci, usuario, telCel,  senha);
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
    }
}
