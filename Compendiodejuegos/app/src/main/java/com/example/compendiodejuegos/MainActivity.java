package com.example.compendiodejuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button botonEntrar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        i=new Intent(this, Menu.class);

        botonEntrar=findViewById(R.id.buttonEntrar);
        botonEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(i);
    }
}
