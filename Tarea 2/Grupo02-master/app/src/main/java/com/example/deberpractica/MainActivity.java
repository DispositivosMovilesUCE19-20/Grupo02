package com.example.deberpractica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private EditText usuarioTextView;
    private EditText claveTextView;
    String usuarioTxt;
    String claveTxt;
    private LeerArchivo archivos=new LeerArchivo();
    private ListaEstudiantes arch=new ListaEstudiantes();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) findViewById(R.id.buttonIngreso);
        btn2=(Button) findViewById(R.id.buttonRegistro);
        usuarioTextView = (EditText) findViewById(R.id.editText);//texto de ingreso de usuario
        claveTextView=(EditText)findViewById(R.id.editText2);//texto de ingreso de clave
        cargarpreferencias();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioTxt= (String) usuarioTextView.getText().toString();
                claveTxt= (String) claveTextView.getText().toString();

                ArrayList<String> usuariosDetalles =archivos.leer();
                ArrayList<String> usuariosAreglos= new ArrayList<String>();
                ArrayList<String> claveAreglos= new ArrayList<String>();;
                for (int i = 0; i < usuariosDetalles.size(); i++) {
                    System.out.println(usuariosDetalles.get(i)+"leer");
                    String [] parts = usuariosDetalles.get(i).split(" ");
                    System.out.println(parts.length+"tamaño");
                    String usuarioi = parts[0];
                    System.out.println(usuarioi+" vector usuario");
                    String clavei = parts[1];
                    System.out.println(usuarioi + " - " + clavei);
                    usuariosAreglos.add(usuarioi);
                    claveAreglos.add(clavei);
                    // }
                }

                for (int i = 0; i < usuariosDetalles.size(); i++) {
                    if (usuariosAreglos.get(i).equals(usuarioTxt)){
                        if(claveAreglos.get(i).equals(claveTxt)){
                            System.out.println("Usuario y clave correcto");
                            Toast.makeText(MainActivity.this,"Usuario y clave correcto",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(MainActivity.this,Listar.class);
                            //intent.putExtra()
                            startActivity(intent);
                            guardarpreferencias();
                            finish();

                        }else{
                            System.out.println("Clave incorrecta");
                            Toast.makeText(MainActivity.this,"Clave incorrecta",Toast.LENGTH_LONG).show();
                        }
                        break;
                    }else{
                        if (i==usuariosAreglos.size()-1){
                            System.out.println("No existe Usuario");
                            Toast.makeText(MainActivity.this,"No existe Usuario",Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Registro.class);
                        //intent.putExtra()
                        startActivity(intent);
                        //eliminarpreferencias();
                        cargarpreferencias();
                        finish();
            }
        });
    }
public void salirApp(View view){
        finish();
}
@Override
public boolean onCreateOptionsMenu(Menu miMenu){
getMenuInflater().inflate(R.menu.menu, miMenu);
return true;
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                //intent.putExtra()
                startActivity(intent);
                eliminarpreferencias();
                cargarpreferencias();
                finish();
                return true;
            case R.id.salir:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void cargarpreferencias(){
    SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
    String user=preferencias.getString( "user","");
    String pass=preferencias.getString("pass","");
    usuarioTextView.setText(user);
    claveTextView.setText(pass);
}

    public void guardarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user= usuarioTextView.getText().toString();
        String pass= claveTextView.getText().toString();
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("user",user);
        editor.putString("pass",pass);
        editor.commit();
    }
    public void eliminarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.clear();
        editor.commit();
    }

}



