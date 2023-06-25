package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.LoginMVP;
import br.edu.ifsp.dmos.presenter.LoginPresenter;
import br.edu.ifsp.dmos.view.md5.Criptografia;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {

    private EditText textUser;
    private EditText textPassword;
    private Button btnEnter;
    private TextView cadastrar;

    private LoginPresenter presenter;
    private SharedPreferences sharedPreferences;

    private CheckBox lembrarDeMim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findById();
        setListener();

        sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        presenter = new LoginPresenter(this, this);

        boolean lembrarDeMimChecked = sharedPreferences.getBoolean("lembrarDeMim", false);
        lembrarDeMim.setChecked(lembrarDeMimChecked);

        if (lembrarDeMimChecked) {
            String savedUser = sharedPreferences.getString("usuario", "");
            textUser.setText(savedUser);
        }
    }

    public boolean isLembrarDeMimChecked() {
        return lembrarDeMim.isChecked();
    }

    private void findById() {
        textUser = findViewById(R.id.edittext_user);
        textPassword = findViewById(R.id.edittext_password);
        btnEnter = findViewById(R.id.button_enter);
        cadastrar = findViewById(R.id.text_singup_reminder);
        lembrarDeMim = findViewById(R.id.radio_remember_me);
    }

    private void setListener() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = textUser.getText().toString();
                String password = textPassword.getText().toString();
                String criptPass= Criptografia.criptografar(password);
                presenter.login(user, criptPass);

                // Salvar o estado do lembrarDeMim no SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("lembrarDeMim", isLembrarDeMimChecked());
                editor.apply();

                if (isLembrarDeMimChecked()) {
                    // Salvar o usuário no SharedPreferences
                    editor.putString("usuario", user);
                    editor.apply();
                } else {
                    // Remover o usuário do SharedPreferences
                    editor.remove("usuario");
                    editor.apply();
                }
            }
        });

        lembrarDeMim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Salvar o estado do lembrarDeMim no SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("lembrarDeMim", isLembrarDeMimChecked());
                editor.apply();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cadast();
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
