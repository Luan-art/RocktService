package br.edu.ifsp.dmos.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.SignUpMVP;
import br.edu.ifsp.dmos.presenter.SingUpPresenter;

public class SingUpActivity  extends AppCompatActivity implements SignUpMVP.View {

    private EditText edittextNomeCompleto;
    private EditText edittextEmail;
    private EditText edittextDoc;
    private EditText edittextDatanasc;
    private EditText edittextUser;
    private EditText edittextPassword;
    private EditText edittextConfPassword;
    private EditText edittextTelcel;
    private Button btnCad;
    private TextView entrar;
    private SingUpPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        findById();
        setListener();
        presenter = new SingUpPresenter(this, this);
    }

    private void setListener() {
        btnCad.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                String nome = edittextNomeCompleto.getText().toString();
                String email = edittextEmail.getText().toString();
                String doc = edittextDoc.getText().toString();
                String datanasc = edittextDatanasc.getText().toString();
                String username = edittextUser.getText().toString();
                String password = edittextPassword.getText().toString();
                String confPassword = edittextConfPassword.getText().toString();
                String telcel = edittextTelcel.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date utilDate = null;
                try {
                    utilDate = dateFormat.parse(datanasc);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date data = new Date(utilDate.getTime());

                if (nome.isEmpty() || email.isEmpty() || doc.isEmpty() || data == null ||
                        username.isEmpty() || telcel.isEmpty() || password.isEmpty() || confPassword.isEmpty() || !password.equals(confPassword)) {
                    showEmptyFieldsMessage();

                } else {

                    presenter.RealizarCadastro(nome, email, doc, data, username, telcel, password, confPassword);
            }
        }
            });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.Enter();
            }
        });
    }

    private void findById() {
        edittextNomeCompleto = findViewById(R.id.edittext_nomeCompleto);
        edittextEmail = findViewById(R.id.edittext_email);
        edittextDoc = findViewById(R.id.edittext_doc);
        edittextDatanasc = findViewById(R.id.edittext_datanasc);
        edittextUser = findViewById(R.id.edittext_user);
        edittextPassword = findViewById(R.id.edittext_password);
        edittextConfPassword = findViewById(R.id.edittext_confPassword);
        edittextTelcel = findViewById(R.id.edittext_telcel);
        btnCad = findViewById(R.id.btn_cad);
        entrar = findViewById(R.id.entrar);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showEmptyFieldsMessage() {
        Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
    }


}
