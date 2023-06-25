package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.UpdatePasswordMVP;
import br.edu.ifsp.dmos.presenter.UpdatePasswordPresenter;
import br.edu.ifsp.dmos.view.md5.Criptografia;

public class UpdatePasswordActivity extends AppCompatActivity implements UpdatePasswordMVP.View {

    private TextView textOldeSenha;
    private TextView textNewSenha;
    private TextView textConfNewSenha;
    private Button btmSalvarSenha;
    private UpdatePasswordPresenter presenter;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        Intent intent = getIntent();
        if (intent != null) {
            usuario = intent.getStringExtra("usuario");
            setUsuario(usuario);
        }

        findById();
        setListener();
        presenter = new UpdatePasswordPresenter(this, this, usuario);


    }

    private void setUsuario(String usuario) {
        this.usuario = usuario;

    }

    private void setListener() {
        btmSalvarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldSenha = textOldeSenha.getText().toString();
                String newSenha = textNewSenha.getText().toString();
                String confNewSenha = textConfNewSenha.getText().toString();

                if (oldSenha.isEmpty() || newSenha.isEmpty() || confNewSenha.isEmpty()) {
                    showMessage("Preencha todos os campos");
                } else if (newSenha.equals(oldSenha)) {
                    showMessage("A nova senha não pode ser igual à antiga");
                } else if (!newSenha.equals(confNewSenha)) {
                    showMessage("Nova senha e senha confirmada são diferentes");
                } else {
                    String criptograSenha = Criptografia.criptografar(newSenha);
                    presenter.updateSenha(usuario, criptograSenha);
                }
            }
        });
    }

    private void findById() {
        textOldeSenha = findViewById(R.id.edittext_senhaAtual);
        textNewSenha = findViewById(R.id.edittext_novaSenha);
        textConfNewSenha = findViewById(R.id.edittext_confirmarNovaSenha);
        btmSalvarSenha = findViewById(R.id.btn_alterarSenha);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
