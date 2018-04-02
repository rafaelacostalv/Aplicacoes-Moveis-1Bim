package com.example.alunos.jogoadivinha2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.*;
import android.widget.ListView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences arquivo = getPreferences(Context.MODE_PRIVATE);

    }
    private int cont;
    private Random gerador = new Random();
    private int numero = gerador.nextInt(1000)+1;
    private TextView tentativas;
    HashMap<String, String> valores = new HashMap<>();



    public void Confere(View v) {

        EditText userInput = findViewById(R.id.editText);
        String str = userInput.getText().toString();
        SharedPreferences arquivo = getPreferences(Context.MODE_PRIVATE);

        int num = Integer.parseInt(str);
        if (num == numero) {
            TextView etiqueta = (TextView) findViewById(R.id.etiqueta);
            etiqueta.setText(getResources().getString(R.string.lblHello));
            tentativas = (TextView) findViewById(R.id.tentativas);
            tentativas.setText(Integer.toString(cont));
            cont = 0;
            numero = gerador.nextInt(1000)+1;;
        } else {
            cont++;
            TextView etiqueta = (TextView) findViewById(R.id.etiqueta);
            etiqueta.setText(getResources().getString(R.string.lblHello2));
            TextView tentativas = (TextView) findViewById(R.id.tentativas);
            tentativas.setText(Integer.toString(cont));

            if (num > numero) {
                TextView palpite = (TextView) findViewById(R.id.palpite);
                palpite.setText("TENTE UM NÚMERO MENOR"+numero);
            } else {
                TextView palpite = (TextView) findViewById(R.id.palpite);
                palpite.setText("TENTE UM NÚMERO MAIOR");
            }
        }

        for(int i = 0; i < 5; i ++){
            String chave = "tentativa" + Integer.toString(i);
            if(valores.containsKey(chave)){
                i++;
            }else{
                valores.put(chave, chave[i]);
                valores.put("tentativas", tentativas)
            }
        }

        String oValor = tentativas.getText().toString();
        SharedPreferences.Editor editor = arquivo.edit();
        editor.putString("tentativas", oValor);
        editor.commit();
    }
    public void placar(View v){
        Intent i = new Intent(getApplicationContext(), Placar.class);

        SharedPreferences arquivo = getPreferences(Context.MODE_PRIVATE);

        String oValor = arquivo.getString("tentativas", "Nada...");
        tentativas.setText(oValor);
        Log.i("oValor: ", oValor);

        Bundle bundle = new Bundle();
        bundle.putString("oValor", oValor);
        i.putExtras(bundle);
        startActivity(i);
    }

}
