package com.example.barbosa.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference usuarios = referencia.child("usuarios");


       // DatabaseReference usuarioPesquisa = usuarios.child("-LRMHyxec3jmtCwzlAlk");

        //Pesquisa utilizando Query
       // Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Luciano");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);


        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //ex: Uma opção de recuperar as informações
                //Usuario dadosUsuario = dataSnapshot.getValue(Usuario.class);
                // Log.i("usuarios: ", "nome: " + dadosUsuario.getNome());

                Log.i("usuarios: ", dataSnapshot.getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*Usuario usuario = new Usuario();
        usuario.setNome("Marcelo");
        usuario.setSobrenome("Cesar");
        usuario.setIdade("29");

        //Comando push para adicionar identificadores únicos
        usuarios.push().setValue(usuario); */

        //Deslogar usuario
        //  usuario.signOut();

        //Para logar usuario
       /* usuario.signInWithEmailAndPassword("popo@gmail.com", "123456789")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Log.i("SingIn", " Sucesso a logar ");
                        }else {
                            Log.i("SingIn", " Erro ao logar ");
                        }

                    }
                });

*/



        /*Verificar usuario logado

        if (usuario.getCurrentUser() != null){
            Log.i("CreateUser", " Usuario Logado ");
        }else {
            Log.i("CreateUser", " Usuario nao logado ");
        }
*/


        //Cadastrar o usuário no firebase estaticamente
/*
        usuario.createUserWithEmailAndPassword(
                "popo@gmail.com", "123456789")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Log.i("CreateUser", " Sucesso ao cadastrar ");

                }else {

                    Log.i("CreateUser", " erro ao cadastrar ");

                }

            }
        });
        */


        // DatabaseReference produtos = referencia.child("produtos");

        //salvar dados no firebase
        /*
        Produtos produto = new Produtos();
        produto.setDescricao("acer250");
        produto.setMarca("Acer");
        produto.setPreco(2500);

        produtos.child("003").setValue(produto);*/
/*
        //recuperar dados do Firebase usando o Listener(ouvinte)
        produtos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
}
