package com.example.compendiodejuegos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Ahorcado extends AppCompatActivity implements View.OnClickListener{

    CharSequence[] palabras={"Rockstar","video","television","minecraft","mojan","android","movil","microcontrolador","java","aplicacion"};
    CharSequence palabra;
    boolean[] letrasEncontradas;
    byte intentos=0;
    TextView textViewPalabra,textViewPalRest,textViewIntentos;
    Button botonReset,botonOk;
    EditText opcion;
    CheckBox xTream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);

        textViewPalabra=findViewById(R.id.textViewPalabra);
        textViewPalRest=findViewById(R.id.textViewPalRest);
        textViewIntentos=findViewById(R.id.textViewIntentos);
        botonReset=findViewById(R.id.buttonReset);
        botonOk=findViewById(R.id.buttonOk);
        opcion=findViewById(R.id.editTextOpcion);
        xTream=findViewById(R.id.radioButtonXTream);
        intentos=0;

        botonReset.setOnClickListener(this);
        botonOk.setOnClickListener(this);

        inicializar();
    }

    private void mensaje(String Mensaje) {
        Toast.makeText(this, Mensaje, Toast.LENGTH_SHORT).show();
    }

    private boolean[] todoAFalso(CharSequence palabra){
        boolean[] aux=new boolean[palabra.length()];
        for(int i=0;i<palabra.length();i++)
            aux[i]=false;
        return aux;
    }

    private boolean letraEncontrada(CharSequence letra,CharSequence palabra){
        boolean aux=false;
        for(int i=0;i<palabra.length();i++){
            if(palabra.charAt(i)==letra.charAt(0)){
                aux=true;
                letrasEncontradas[i]=true;
            }
        }
        return aux;
    }

    private boolean ganaron(){
        for(int i=0;i<palabra.length();i++)
            if(!letrasEncontradas[i])return false;
        return true;
    }

    private boolean ganaron(CharSequence palabraDada){
        for(int i=0;i<palabra.length();i++)
            if(palabraDada.charAt(i)!=palabra.charAt(i))return false;
        return true;
    }

    private void iniciarIntentos(){
        intentos=0;
        RadioButton[] Radio=new RadioButton[3];
        Radio[2]=findViewById(R.id.radioButtonFacil);
        Radio[1]=findViewById(R.id.radioButtonMedio);
        Radio[0]=findViewById(R.id.radioButtonDificil);
        for(int i=0;i<3;i++){
            intentos+=2;
            if(Radio[i].isChecked())break;
        }
    }

    private void inicializar(){
        Random r=new Random(System.nanoTime());
        palabra=palabras[r.nextInt(palabras.length)];

        letrasEncontradas=todoAFalso(palabra);

        iniciarIntentos();
        actualizar();
        opcion.setText("");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonOk){
            ok();
        }else if(v.getId()==R.id.buttonReset){
            inicializar();
        }
    }

    private void dialogoGanador(){
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        alerta.setTitle("Ganador");
        alerta.setIcon(R.drawable.trofeo);
        alerta.setPositiveButton(R.string.Ganador, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inicializar();
            }
        });
        alerta.show();
    }

    private void dialogoPerdedor(){
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        alerta.setTitle("Has muerto X_X");
        alerta.setIcon(R.drawable.ahorcado);
        alerta.setPositiveButton(R.string.Perdedor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inicializar();
            }
        });
        alerta.show();
    }

    public void ocultar(){
        String aux="";
        for(int i=0;i<palabra.length();i++){
            if(letrasEncontradas[i]){
                aux+=palabra.charAt(i);
            }else{
                aux+="_";
            }
        }
        textViewPalabra.setText(aux);
    }

    private void actualizar(){
        if(!xTream.isChecked())
            for(int i=0;i<palabra.length();i++)
                if(palabra.charAt(i)=="a".charAt(0)||palabra.charAt(i)=="e".charAt(0)||palabra.charAt(i)=="i".charAt(0)||palabra.charAt(i)=="o".charAt(0)||palabra.charAt(i)=="u".charAt(0))
                    letrasEncontradas[i]=true;
        ocultar();
        textViewIntentos.setText("intentos restantes: "+intentos);
        textViewPalRest.setText("Letras restantes: "+letrasRestantes());

    }

    private byte letrasRestantes(){
        byte c=0;
        for(boolean i:letrasEncontradas)
            if(!i)c+=1;
        return c;
    }

    private void ok(){
        if(opcion.getText().length()==1){
            if(letraEncontrada(opcion.getText(),palabra)){
                actualizar();
                if(ganaron()){
                    dialogoGanador();
                }
            }else{
                intentos--;
                actualizar();
                if(intentos<=0){
                    dialogoPerdedor();
                }
            }
        }else{
            if(ganaron(opcion.getText())){
                todoVerdadero();
                actualizar();
                dialogoGanador();
            }else{
                intentos--;
                actualizar();
                if(intentos<=0){
                    dialogoPerdedor();
                }
            }
        }
    }

    private void todoVerdadero(){
        for(int i=0;i<palabra.length();i++)
            letrasEncontradas[i]=true;
    }

    public void onFragmentInteraction(Uri uri) {

    }
}
