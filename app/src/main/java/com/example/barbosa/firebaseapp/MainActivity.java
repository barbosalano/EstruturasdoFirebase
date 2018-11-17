package com.example.barbosa.firebaseapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button buttonUpload;
    private ImageView imageFoto;

    //private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    // private FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUpload = findViewById(R.id.buttonUpload);
        imageFoto = findViewById(R.id.imageFoto);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Configura para imagem ser salva em memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                //Recupera bitmap da imagem (imagem a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                //Comprimindo bitmap para formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                //converter a imagem para picel brutos em uma matriz de bytes para poder enviar para o Firebase
                byte[] dadosImagem = baos.toByteArray();

                //Definindo nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");


                StorageReference imagemRef = imagens.child("841586d2-d9df-4e23-9491-0c60ea66e166.jpeg");

                //Baixando arquivos

                Glide.with(MainActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(imagemRef)
                        .into(imageFoto);





                //Deletando arquivos

               /* imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Erro ao deletar o arquivo " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,
                                "Sucesso ao deletar arquivo " ,
                                Toast.LENGTH_LONG).show();
                    }
                });*/

                //nome da imagem automatica
                /*
                String nomeImagem = UUID.randomUUID().toString();
                StorageReference imagemRef = imagens.child(nomeImagem + ".jpeg");

                //Retora objeto que irá controlar o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this,
                                "Upload da imagem falhou: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                        Toast.makeText(MainActivity.this,
                                "Sucesso ao carregar a imagem: " ,
                                Toast.LENGTH_LONG).show();

                    }
                });*/


            }
        });

        //DatabaseReference usuarios = referencia.child("usuarios");

        // DatabaseReference usuarioPesquisa = usuarios.child("-LRMHyxec3jmtCwzlAlk");

        //Pesquisa utilizando Query
        // Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Luciano");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
        // Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);


        //Aplicando filtros com operadores relacionais Maior ou igual (>=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt("40");

        //Aplicando filtros com operadores relacionais Menor ou igual (<=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt("40");

        //Aplicando filtros utilizando os 2 relacionais
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt("20").endAt("40");

        //Aplicando filtros utilizando os 2 relacionais
        //Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("L").endAt("L" + "\uf8ff");

        //Query usuarioPesquisa = usuarios.orderByChild("nome").endAt("L" + "\uf8ff");


        /* usuarioPesquisa.addValueEventListener(new ValueEventListener() {
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
        });*/

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
