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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class Listar extends AppCompatActivity {
    private TextView usuariosListar;
    private Spinner sp1;
    private LeerArchivo archivos=new LeerArchivo();
    ArrayList<String> usuariosDetalles;

    private TextView textoservicio;


    private ObtenerServicio obtener = new ObtenerServicio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        textoservicio=(TextView)findViewById(R.id.textView5);
        textoservicio.setText(obtener.getDato());

        usuariosListar = (TextView) findViewById(R.id.textView3);
        sp1=(Spinner)findViewById(R.id.spinner4);

        usuariosDetalles =archivos.leer();
        ArrayList<String> usuariosAreglos= archivos.leerColumna(0);
        ArrayList<String> claveAreglos= archivos.leerColumna(1);;

        //String [] usuaruiosAreglo ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,usuariosAreglos);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {
                        Toast.makeText(spn.getContext(), "Has seleccionado " +
                                        spn.getItemAtPosition(posicion).toString(),
                                Toast.LENGTH_LONG).show();
                        String [] parts = usuariosDetalles.get(posicion).split(" ");
                        //System.out.println(parts.length+"tamaño");
                        if (parts.length==10) {
                            String usuarioi = parts[0];
                            String clavei = parts[1];
                            String nombre = parts[2];
                            String apellido = parts[3];
                            String email = parts[4];
                            String telefono = parts[5];
                            String genero = parts[6];
                            String fecha = parts[7];
                            String asignaturas = parts[8];
                            String becado = parts[9];
                            String detallesUsuarioElegido = "nombre: " + nombre + "\n" + "apellido: " + apellido + "\n" + "email: " + email + "\n" + "telefono: " + telefono + "\n" + "genero: " + genero + "\n" + "fecha: " + fecha + "\n" + "asignatura: " + asignaturas + "\n" + "becado: " + becado;
                            usuariosListar.setText(detallesUsuarioElegido);
                        }else{
                            Toast.makeText(Listar.this,"ERROR DE ENTRADA DE DATOS",Toast.LENGTH_LONG).show();

                        }
                    }
                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });

    }
    public boolean onCreateOptionsMenu(Menu miMenu){
        getMenuInflater().inflate(R.menu.menu, miMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                Intent intent=new Intent(Listar.this,MainActivity.class);
                //intent.putExtra()
                startActivity(intent);
                eliminarpreferencias();
                //cargarpreferencias();
                finish();
                return true;
            case R.id.salir:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    public void eliminarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.clear();
        editor.commit();
    }


}

