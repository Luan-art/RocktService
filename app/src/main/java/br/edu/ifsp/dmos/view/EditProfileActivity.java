package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.EditProfilePresenterMVP;
import br.edu.ifsp.dmos.presenter.EditProfilePresenter;

public class EditProfileActivity extends AppCompatActivity implements EditProfilePresenterMVP.View {

    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText docEditText;
    private EditText dataNascEditText;
    private EditText userEditText;
    private EditText telCelEditText;
    private EditText endEditText;
    private EditText cidadeEditText;
    private Spinner estadoSpinner;
    private Button salvarButton;
    private EditProfilePresenter presenter;
    private String idUsuario;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bundle = getIntent().getExtras();
        idUsuario = bundle.getString("idUsuarioBundle");
        Log.d("nome Do Usuario no edit profile activity", "Value: " + (idUsuario));


        findById();
        setListener();
        presenter = new EditProfilePresenter(this, this);
    }

    private void setListener() {
        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String doc = docEditText.getText().toString();
                String dataNasc = dataNascEditText.getText().toString();
                String usuario = userEditText.getText().toString();
                String telCel = telCelEditText.getText().toString();
                String endereco = endEditText.getText().toString();
                String cidade = cidadeEditText.getText().toString();
                String estado = estadoSpinner.getSelectedItem().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date utilDate = null;
                try {
                    utilDate = dateFormat.parse(dataNasc);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date data = new Date(utilDate.getTime());

                if (nome.isEmpty() || email.isEmpty() || doc.isEmpty() || dataNasc.isEmpty() ||
                        usuario.isEmpty() || telCel.isEmpty() || endereco.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
                    showMessage("Preencha todos os campos");
                } else {
                    presenter.updatePerfil(idUsuario, nome, email, doc,  dataNasc,
                            usuario, telCel, endereco,cidade, estado);
                }
            }
        });
    }

    private void findById() {
        nomeEditText = findViewById(R.id.edittext_nomeUserEdit);
        emailEditText = findViewById(R.id.edittext_emailUserEdit);
        docEditText = findViewById(R.id.edittext_DocUserEdit);
        dataNascEditText = findViewById(R.id.edittext_datanascUserEdit);
        userEditText = findViewById(R.id.edittext_UserEdit);
        telCelEditText = findViewById(R.id.edittext_telcelUser);
        endEditText = findViewById(R.id.edittext_endUserEdit);
        cidadeEditText = findViewById(R.id.edittext_cidadeUserEdit);
        estadoSpinner = findViewById(R.id.spinner_estado);
        salvarButton = findViewById(R.id.btnContratar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSpinner.setAdapter(adapter);
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
