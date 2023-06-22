package br.edu.ifsp.dmos.presenter;

import static br.edu.ifsp.dmos.R.string.string_campos_vazios;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.LoginMVP;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.SingUpActivity;

public class LoginPresenter implements LoginMVP.Presenter {

    private LoginMVP.View view;
    private Context context;

    public LoginPresenter(LoginMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void Login(String user, String password) {

        if (user.isEmpty() || password.isEmpty()) {
            view.showEmptyFieldsMessage();

        } else{
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    public void Cadast() {
        Intent intent = new Intent(context, SingUpActivity.class);
        context.startActivity(intent);
    }
}
