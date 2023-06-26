package br.edu.ifsp.dmos.view;

import static br.edu.ifsp.dmos.constants.Constants.FIELD_ADD_INFO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_CATEGORIA;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_COMENT;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_FORMAS_DE_PAGAMENTO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_FORMA_EXECUCAO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_ID_PROFISSIONAL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_MEDIA_PRECO;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME_PROFISSIONAL;
import static br.edu.ifsp.dmos.constants.Constants.FIELD_NOME_SERVICO;
import static br.edu.ifsp.dmos.constants.Constants.SERVICE_COLLECTION;
import static br.edu.ifsp.dmos.constants.Constants.USERS_COLLECTION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.model.entites.User;
import br.edu.ifsp.dmos.mvp.ServiceDescriptionMVP;
import br.edu.ifsp.dmos.presenter.ServiceDescriptionPresenter;

public class ServiceDescriptionActivity extends AppCompatActivity implements ServiceDescriptionMVP.View{

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

    private User user;

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
        preencher();

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

        String chave = getIntent().getStringExtra("chave");
        Log.d("DEBUG", "Valor da chave: " + chave);


        //BUSCA POR CAMPOS DE SERVICE
        //----------------------------
        //----------------------------
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection(SERVICE_COLLECTION)
                .whereEqualTo(FieldPath.documentId(), chave)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                //textNomeProf.setText(documentSnapshot.getString(FIELD_NOME_SERVICO));
                                //service.idProfissional = documentSnapshot.getString(FIELD_ID_PROFISSIONAL);
                                textNomeProf.setText(documentSnapshot.getString(FIELD_NOME_PROFISSIONAL));
                                //service.categoria = documentSnapshot.getString(FIELD_CATEGORIA);
                                mediaPrecoNumber.setText(String.valueOf(documentSnapshot.getDouble(FIELD_MEDIA_PRECO)));
                                formaPagamentoInfo.setText(documentSnapshot.getString(FIELD_FORMAS_DE_PAGAMENTO));
                                formaExecucaoInfo.setText(documentSnapshot.getString(FIELD_FORMA_EXECUCAO));
                                addInfoInfo.setText(documentSnapshot.getString(FIELD_ADD_INFO));
                                //service.coment = documentSnapshot.getString(FIELD_COMENT);
                                String idProfissional = documentSnapshot.getString(FIELD_ID_PROFISSIONAL);




                            }
                        }
                    }
                });
    }

    private void setListener() {
        btnContratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.contratarTarefa(service);
            }
        });
    }

    /*
    private void recuperarService() {
        preencher();
    }
     */
}
