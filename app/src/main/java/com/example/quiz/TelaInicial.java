package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaInicial extends AppCompatActivity {
    private ImageButton btnLogin;
    private TextView convidado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.tela_inicial);

        btnLogin = (ImageButton) findViewById(R.id.btnLogin);
        convidado = (TextView) findViewById(R.id.convidado);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicial.this, Login.class);
                startActivity(intent);
            }
        });

        convidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // colocar tela de destino no lugar de CadastroScreen
                Intent intent = new Intent(TelaInicial.this, CadastroScreen.class);
                startActivity(intent);
            }
        });
    }



}