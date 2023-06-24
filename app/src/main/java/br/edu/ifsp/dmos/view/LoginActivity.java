package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.LoginMVP;
import br.edu.ifsp.dmos.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {

    private EditText textUser;
    private EditText textPassword;
    private Button btnEnter;
    private TextView cadastrar;

    private LoginPresenter presenter;
    private SharedPreferences sharedPreferences;

    private RadioButton lembrarDeMim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        presenter = new LoginPresenter(this, this);

        findById();
        setListener();

        // Carregar o usuário salvo, se existir
        if (lembrarDeMim.isChecked()) {
            String savedUser = sharedPreferences.getString("usuario", "");
            textUser.setText(savedUser);
        }
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
                presenter.login(user, password);

                if (lembrarDeMim.isChecked()) {
                    // Salvar o usuário no SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usuario", user);
                    editor.apply();
                }
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
