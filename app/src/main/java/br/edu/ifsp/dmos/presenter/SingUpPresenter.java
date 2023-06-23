package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.User;
import br.edu.ifsp.dmos.mvp.SignUpMVP;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.LoginActivity;
import br.edu.ifsp.dmos.view.SingUpActivity;

public class SingUpPresenter implements SignUpMVP.Presenter {

    private SignUpMVP.View view;
    private  Context context;

    private String firestoreId = "";

    private FirebaseFirestore database;




    public SingUpPresenter(SignUpMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();


    }

    @Override
    public void Enter() {
        Intent intent = new Intent(context, LoginPresenter.class);
        context.startActivity(intent);
    }

    @Override
    public void RealizarCadastro(String nome, String email, String doc, Date dataNasci,
                                 String usuario, String telCel,  String senha, String confSenha) {

        User user;
        user = new User( nome,  email,  doc, dataNasci, usuario,  telCel,
                null ,null, null,  senha);

        CollectionReference listUsuarios = database.collection(Constants.USERS_COLLECTION);

        listUsuarios.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(view.getContext(), "Usuario cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(view.getContext(), "Erro ao cadastrar Usuario.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean isNewUser() {
        return firestoreId.isEmpty();

    }
}
