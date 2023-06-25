package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quiz.dao.SqlliteConnector;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class CadastroScreen extends AppCompatActivity {

    TextInputEditText inputEmail, inputSenha;
    String email, senha;
    ImageButton btnFinalizar;
    AutoCompleteTextView actvDia, actvMes, actvAno;
    ArrayAdapter<String> itensDia, itensMes, itensAno;
    String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] mes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    String[] ano = {"1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934",
            "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947",
            "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960",
            "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973",
            "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986",
            "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
            "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
            "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        inputEmail = findViewById(R.id.email);
        inputSenha = findViewById(R.id.senha);

        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailValido(inputEmail.getText().toString()) && senhaValida(inputSenha.getText().toString())
                        && dataValida(actvDia.getText().toString(), actvMes.getText().toString(), actvAno.getText().toString())) {
                    System.out.println("Clicou no meu botão");
                    SqlliteConnector connector = new SqlliteConnector(getApplicationContext());
                    String name = inputEmail.getText().toString();
                    String password = inputSenha.getText().toString();

                    if(!(connector.checkUser(name,password))){
                        System.out.println(("Entrou"));
                        Cadastro novo_cadastro = new Cadastro(name,password);
                        connector.cadastro(novo_cadastro);
                        System.out.println("Cadastrou");

                        Intent intent = new Intent(CadastroScreen.this, Login.class);
                        startActivity(intent);
                    }

                }
            }
        });

        actvDia = findViewById(R.id.actvDia);
        actvMes = findViewById(R.id.actvMes);
        actvAno = findViewById(R.id.actvAno);

        itensDia = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, dias);
        itensMes = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mes);
        itensAno = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ano);

        actvDia.setAdapter(itensDia);
        actvMes.setAdapter(itensMes);
        actvAno.setAdapter(itensAno);

        actvDia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                actvDia.showDropDown();
                return false;
            }
        });

        actvMes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                actvMes.showDropDown();
                return false;
            }
        });

        actvAno.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                actvAno.showDropDown();
                return false;
            }
        });

        actvDia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        actvMes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        actvAno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private boolean emailValido(String email) {

        if(email.isEmpty()){
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            return false;
        }

        String regex = "^([a-zA-Z0-9_@.]){10,50}$";
        boolean isValid = Pattern.matches(regex, email);

        if (!isValid) {
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean senhaValida(String senha) {

        if(senha.isEmpty()){
            Toast.makeText(this, "A senha deve conter ao menos 8 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        String regex = "^.{8,}$";
        boolean isValid = Pattern.matches(regex, senha);

        if (!isValid) {
            Toast.makeText(this, "A senha deve conter ao menos 8 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean dataValida(String dia, String mes, String ano) {

        if(dia.isEmpty()){
            Toast.makeText(this, "Informe o dia", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(mes.isEmpty()){
            Toast.makeText(this, "Informe o mês", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(ano.isEmpty()){
            Toast.makeText(this, "Informe o ano", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

}