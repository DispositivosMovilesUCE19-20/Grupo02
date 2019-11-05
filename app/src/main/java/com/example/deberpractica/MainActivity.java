package com.example.deberpractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private EditText usuarioTextView;
    private EditText claveTextView;
    String usuarioTxt;
    String claveTxt;
    private String archivo ="miarchivo7";
    private String carpeta= "/archivos/";
    File file;
    String file_path="";
    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) findViewById(R.id.buttonIngreso);
        btn2=(Button) findViewById(R.id.buttonRegistro);
        usuarioTextView = (EditText) findViewById(R.id.editText);//texto de ingreso de usuario
        claveTextView=(EditText)findViewById(R.id.editText2);//texto de ingreso de clave
        this.file_path=(Environment.getExternalStorageDirectory()+this.carpeta);
        File localFile=new File(this.file_path);
        if(!localFile.exists()){
            localFile.mkdir();
        }
        this.name=(this.archivo +".txt");
        this.file=new File(localFile,this.name);
        try{
            this.file.createNewFile();
            escribir("admin", "admin","david","lara","dllara@uce.edu.ec","0998124155","hombre","23/10/1995","Fisica-Ciencias-Informatica-","si");
        }catch (IOException e){
            e.printStackTrace();
        }
        //String usuariosDetalles [];
        //String usuariosAreglos[];
        //String claveAreglos[];



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioTxt= (String) usuarioTextView.getText().toString();
                claveTxt= (String) claveTextView.getText().toString();

                //if(file.exists()){
                String usuariosDetalles[] =leer();
                String usuariosAreglos[]= new String[usuariosDetalles.length];
                String claveAreglos[] = new String[usuariosDetalles.length];
                for (int i = 0; i < usuariosDetalles.length; i++) {
                    System.out.println(usuariosDetalles[i]+"leer");
                    String [] parts = usuariosDetalles[i].split(" ");
                    System.out.println(parts.length+"tamaño");
                    String usuarioi = parts[0];
                    System.out.println(usuarioi+" vector usuario");
                    String clavei = parts[1];
                    System.out.println(usuarioi + " - " + clavei);
                    usuariosAreglos[i] = usuarioi;
                    claveAreglos[i] = clavei;
                    // }
                }

                for (int i = 0; i < usuariosDetalles.length; i++) {
                    if (usuariosAreglos[i].equals(usuarioTxt)){
                        if(claveAreglos[i].equals(claveTxt)){
                            System.out.println("Usuario y clave correcto");
                            Toast.makeText(MainActivity.this,"Usuario y clave correcto",Toast.LENGTH_LONG).show();
                        }else{
                            System.out.println("Clave incorrecta");
                            Toast.makeText(MainActivity.this,"Clave incorrecta",Toast.LENGTH_LONG).show();
                        }
                        break;
                    }else{
                        if (i==usuariosAreglos.length-1){
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
            }
        });
    }
    public void escribir(String usua, String clave,String nombre,String apellido,String email,String telefono,String genero,String fecha,String asignaturas,String becado){
        FileWriter fichero=null;
        PrintWriter pw =null;
        try{
            //fichero=new FileWriter(file);
            fichero=new FileWriter(file.getAbsoluteFile(), true);
            pw=new PrintWriter(fichero);
            //pw.println(usuarioTextView.getText().toString());
            pw.println(usua+" "+clave+" "+nombre+" "+apellido+" "+email+" "+telefono+" "+genero+" "+fecha+" "+asignaturas+" "+becado);
            pw.flush();
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (null!= fichero)
                    fichero.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
    public  String[] leer(){
        String contenido="";
        FileInputStream fin=null;
        String matriz[] = new String[1000];

        int n=0;
        try{
            fin= new FileInputStream(file);

        }catch(FileNotFoundException e1){
            e1.printStackTrace();
        }
        InputStreamReader archivo=new InputStreamReader(fin);
        BufferedReader br=new BufferedReader(archivo);
        int ascii;
        try{
            //while ((ascii=br.read())!= -1){
              // char caracter=(char) ascii;
               //contenido+=caracter;

            //}
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                matriz[i] = linea;
                i=i+1;
            }
            n=i;


        }catch (IOException e){
            e.printStackTrace();
        }
        //Log.e(tag="leer",contenido);

        String usuariosDetalles[] = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.println(matriz[i]);
            usuariosDetalles[i]  = matriz[i] ;
            System.out.println(usuariosDetalles[i]+"metodo leer");
        }
//       claveTextView.setText(usuariosDetalles[n-1]);
        //System.out.println(usuariosDetalles[n-1]+"llllllllllllllllllllllllll");

        return usuariosDetalles;
    }

}



