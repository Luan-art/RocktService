package br.edu.ifsp.dmos.view;

import static br.edu.ifsp.dmos.R.string.string_campos_vazios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.LoginMVP;
import br.edu.ifsp.dmos.mvp.MainMVP;
import br.edu.ifsp.dmos.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {

    private EditText textUser;
    private EditText textPassoword;
    private Button btnEnter;
    private TextView cadastrar;

    private LoginPresenter presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findById();
        setListener();
        presenter = new LoginPresenter(this, this);
    }


    private void findById() {
        textUser = findViewById(R.id.edittext_user);
        textPassoword = findViewById(R.id.edittext_password);
        btnEnter = findViewById(R.id.button_enter);
        cadastrar = findViewById(R.id.text_singup_reminder);
    }

    private void setListener() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.Login( textUser.getText().toString(), textPassoword.getText().toString());
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.Cadast();
            }
        });
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showErrorMessage(String erro_ao_buscar_usuário) {
        Toast.makeText(this, erro_ao_buscar_usuário, Toast.LENGTH_SHORT).show();
    }


}