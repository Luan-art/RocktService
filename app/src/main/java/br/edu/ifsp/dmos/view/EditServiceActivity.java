package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
import br.edu.ifsp.dmos.mvp.EditServiceMVP;
import br.edu.ifsp.dmos.presenter.EditProfilePresenter;
import br.edu.ifsp.dmos.presenter.EditServicePresenter;

public class EditServiceActivity extends AppCompatActivity implements EditServiceMVP.View {

    private EditServicePresenter presenter;

    private EditText edittextNomeServico;
    private Spinner spinnerCategoria;
    private EditText edittextMediaPreco;
    private EditText edittextFormasPagamento;
    private EditText edittextFormaExecucao;
    private EditText editText;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar Serviço Oferecido");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findById();
        setListener();
        presenter = new EditServicePresenter(this, this);

    }


    private void findById() {
        edittextNomeServico = findViewById(R.id.edittext_nomeServico);
        spinnerCategoria = findViewById(R.id.spinner_Categoria);
        edittextMediaPreco = findViewById(R.id.edittext_mediaPreco);
        edittextFormasPagamento = findViewById(R.id.edittext_formasPagamento);
        edittextFormaExecucao = findViewById(R.id.edittext_formaExecucao);
        editText = findViewById(R.id.editText);
        btnSave = findViewById(R.id.btn_save);

        cratSimpleAdpater();

    }

    private void cratSimpleAdpater() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategoria.setAdapter(adapter);
    }

    private void setListener() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeServico = edittextNomeServico.getText().toString();
                String categoria = spinnerCategoria.getSelectedItem().toString();
                String mediaPreco = edittextMediaPreco.getText().toString();
                String formasPagamento = edittextFormasPagamento.getText().toString();
                String formaExecucao = edittextFormaExecucao.getText().toString();
                String text = editText.getText().toString();


                if (nomeServico.isEmpty() || categoria.isEmpty() || mediaPreco.isEmpty() || formasPagamento.isEmpty() ||
                        formaExecucao.isEmpty() ||text.isEmpty()) {
                    showMessage("Preencha todos os campos");
                } else {
                    presenter.updateService(nomeServico, categoria, mediaPreco, formasPagamento,  formaExecucao,
                            text);
                }
            }
        });
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String mensage) {
        Toast.makeText(this, mensage, Toast.LENGTH_SHORT).show();
    }
}