package com.example.compendiodejuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tic_tac extends AppCompatActivity implements View.OnClickListener {

    Button[][] ButtonJuego = new Button[3][3];
    boolean turnoX=true , noGanaron=true;
    CharSequence guion,x="X",o="O";//esta variable intenta obtener el guion de los botones por ploblemas con charSequence

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac);
        getSupportActionBar().hide();
        crearArreglo();//crearArreglo lo que hace es agregar los botones a ButtonJuego y darle el valor a guion;
        crearOnClick();
    }

    private void crearArreglo() {
        ButtonJuego[0][0]=findViewById(R.id.button00);
        ButtonJuego[0][1]=findViewById(R.id.button01);
        ButtonJuego[0][2]=findViewById(R.id.button02);
        ButtonJuego[1][0]=findViewById(R.id.button10);
        ButtonJuego[1][1]=findViewById(R.id.button11);
        ButtonJuego[1][2]=findViewById(R.id.button12);
        ButtonJuego[2][0]=findViewById(R.id.button20);
        ButtonJuego[2][1]=findViewById(R.id.button21);
        ButtonJuego[2][2]=findViewById(R.id.button22);
        guion=ButtonJuego[0][0].getText();
    }

    private void crearOnClick(){
        for(Button[] i:ButtonJuego)
            for(Button c:i)
                c.setOnClickListener(this);
        Button Reinciar=findViewById(R.id.buttonReiniciar);
        Reinciar.setOnClickListener(this);
    }
    private void alerta(String s){
        final CharSequence[] opciones={"Nuevo Juego"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(this);
        alertOpciones.setTitle(s);
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reiniciar();
            }
        });
        alertOpciones.show();
    }


    private boolean Ganaron(){
        for(int i=0;i<3;i++)
            for(int c=0;c<3;c++){
                if(ButtonJuego[i][c].getText()==guion)break;
                if(ButtonJuego[i][0].getText()!=ButtonJuego[i][c].getText())break;
                if(c==2)return true;
            }
        for(int i=0;i<3;i++)
            for(int c=0;c<3;c++){
                if(ButtonJuego[c][i].getText()==guion)break;
                if(ButtonJuego[0][i].getText()!=ButtonJuego[c][i].getText())break;
                if(c==2)return true;
            }
        if(ButtonJuego[0][0].getText()==ButtonJuego[1][1].getText()&&ButtonJuego[1][1].getText()==ButtonJuego[2][2].getText()&&ButtonJuego[0][0].getText()!=guion)return true;
        if(ButtonJuego[0][2].getText()==ButtonJuego[1][1].getText()&&ButtonJuego[1][1].getText()==ButtonJuego[2][0].getText()&&ButtonJuego[0][2].getText()!=guion)return true;
        return false;
    }

    private void mostrar(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    private boolean empate(){
        for(Button[] i:ButtonJuego)
            for(Button c:i)
                if(c.getText()==guion)return false;
        return true;
    }

    @Override
    public void onClick(View v) {
        Button aux=findViewById(v.getId());
        CharSequence texto=aux.getText();
        if(texto==guion&&noGanaron){
            if(turnoX){
                aux.setText(x);
                turnoX=false;
            }else{
                aux.setText(o);
                turnoX=true;
            }if(Ganaron()){
                TextView TextViewGanador=findViewById(R.id.textViewGanador);
                if(turnoX) {
                    TextViewGanador.setText("Ganador O");
                    noGanaron = false;
                    alerta("Ganador O");
                }else{
                    TextViewGanador.setText("Ganador X");
                    noGanaron = false;
                    turnoX = true;
                    alerta("Ganador X");
                }
            }else if(empate()){
                alerta("Empate");
            }
        }else if(v.getId()==R.id.buttonReiniciar){
            reiniciar();
        }
    }

    private void reiniciar(){
        noGanaron=true;
        CharSequence cs=guion;
        for(Button[] i:ButtonJuego)
            for(Button c:i)
                c.setText(cs);
        TextView TextViewGanador=findViewById(R.id.textViewGanador);
        TextViewGanador.setText("");
    }
}
