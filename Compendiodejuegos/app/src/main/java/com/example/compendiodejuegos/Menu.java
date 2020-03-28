package com.example.compendiodejuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button botonTic,botonAhorcar;
    Intent iTic, iAho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        botonTic=findViewById(R.id.buttonTicTac);
        botonAhorcar=findViewById(R.id.buttonAhorcado);
        iTic=new Intent(this,Tic_tac.class);
        iAho=new Intent(this, Ahorcado.class);
        botonTic.setOnClickListener(this);
        botonAhorcar.setOnClickListener(this);
    }

    /*public void onClickTicTac(View v){
        Intent i=new Intent(this,Tic_tac.class);
        startActivity(i);
    }

    public void onClickAhorcado(View v){
        Intent i=new Intent(this,Ahorcado.class);
        startActivity(i);
    }*/


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonTicTac){
            startActivity(iTic);
        }else if(v.getId()==R.id.buttonAhorcado){
            startActivity(iAho);
        }
    }
}
