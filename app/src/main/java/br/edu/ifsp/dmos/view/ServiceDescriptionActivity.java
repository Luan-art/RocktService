package br.edu.ifsp.dmos.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ServiceDescriptionMVP;
import br.edu.ifsp.dmos.presenter.ServiceDescriptionPresenter;

public class ServiceDescriptionActivity extends AppCompatActivity implements ServiceDescriptionMVP.View, ServiceDescriptionMVP.OnServiceRetrievedListener {

    private Button btnContratar;
    private TextView textNomeProf;
    private TextView tel;
    private TextView email;
    private TextView mediaPrecoNumber;
    private TextView formaPagamentoInfo;
    private TextView formaExecucaoInfo;
    private TextView addInfoInfo;

    private ServiceDescriptionPresenter presenter;
    private Service service; // Armazena o objeto Service recuperado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_description);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mais Informações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter = new ServiceDescriptionPresenter(this, this);

        findById();
        setListener();
        recuperarService();
    }

    private void findById() {
        btnContratar = findViewById(R.id.btnContratar);
        textNomeProf = findViewById(R.id.textNomeProf);
        tel = findViewById(R.id.tel);
        email = findViewById(R.id.email);
        mediaPrecoNumber = findViewById(R.id.mediaPrecoNumber);
        formaPagamentoInfo = findViewById(R.id.formaPagamentoInfo);
        formaExecucaoInfo = findViewById(R.id.formaExecucaoInfo);
        addInfoInfo = findViewById(R.id.addInfoInfo);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void preencher() {
        textNomeProf.setText(service.getNomeProfissional());
        tel.setText("");
        email.setText("");
        mediaPrecoNumber.setText(String.valueOf(service.isMediaPreco()));
        formaPagamentoInfo.setText(service.getFormasDePagamento());
        formaExecucaoInfo.setText(service.getFormaExecucao());
        addInfoInfo.setText(service.getAddInfo());    }

    private void setListener() {
        btnContratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.contratarTarefa(service);
            }
        });
    }

    private void recuperarService() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String taskId = getIntent().getStringExtra("taskId");

        if (taskId != null) {
            DocumentReference docRef = db.collection("Services").document(taskId);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            service = document.toObject(Service.class);
                            preencher();
                        } else {
                            onServiceRetrievalFailed("Document does not exist");
                        }
                    } else {
                        onServiceRetrievalFailed("Error occurred while fetching the document");
                    }
                }
            });
        } else {
            onServiceRetrievalFailed("Invalid document ID");
        }


    }

    @Override
    public Service onServiceRetrieved(Service service) {
        return service;
    }

    @Override
    public void onServiceRetrievalFailed(String errorMessage) {
        Toast.makeText(ServiceDescriptionActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
