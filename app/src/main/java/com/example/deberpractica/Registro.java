package com.example.deberpractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Registro extends AppCompatActivity {
    private EditText usuario;
    private EditText clave;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText celular;
    private RadioButton rb1,rb2;
    private CheckBox check1,check2,check3,check4,check5;
    private Spinner sp1,sp2,sp3;
    private Switch sw1;
    private Button btn1;

    private String archivo ="miarchivo6";
    private String carpeta= "/archivos/";
    File file;
    String file_path="";
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario = (EditText) findViewById(R.id.editText5);
        clave=(EditText)findViewById(R.id.editText6);
        nombre = (EditText) findViewById(R.id.editText7);
        apellido=(EditText)findViewById(R.id.editText8);
        email = (EditText) findViewById(R.id.editText3);
        celular=(EditText)findViewById(R.id.editText4);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton);
        check1=(CheckBox)findViewById(R.id.checkBox);
        check2=(CheckBox)findViewById(R.id.checkBox2);
        check3=(CheckBox)findViewById(R.id.checkBox3);
        check4=(CheckBox)findViewById(R.id.checkBox4);
        check5=(CheckBox)findViewById(R.id.checkBox5);
        sp1=(Spinner)findViewById(R.id.spinner);
        sp2=(Spinner)findViewById(R.id.spinner2);
        sp3=(Spinner)findViewById(R.id.spinner3);
        sw1=(Switch)findViewById(R.id.switch1);
        btn1=(Button) findViewById(R.id.button);
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
        String [] dia ={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15}","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        ArrayAdapter <String> adapter=new ArrayAdapter <String> (this,android.R.layout.simple_list_item_1,dia);
        sp3.setAdapter(adapter);
        String [] mes ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter <String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mes);
        sp1.setAdapter(adapter1);
        String [] anio ={"1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        ArrayAdapter <String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,anio);
        sp2.setAdapter(adapter2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioTxt= (String) usuario.getText().toString();
                String claveTxt= (String) clave.getText().toString();
                String nombreTxt=(String) nombre.getText().toString();
                String apellidoTxt=(String) apellido.getText().toString();
                String emailTxt=(String) email.getText().toString();
                String celularTxt=(String) celular.getText().toString();
                String generoTxt;
                String becadoTxt;
                String asignaturas="";
                if (rb1.isChecked()==true){
                    generoTxt="hombre";
                }else{
                    generoTxt="mujer";
                }
                if (check1.isChecked()==true){
                    asignaturas=asignaturas+"Ciencias-";
                }
                if (check2.isChecked()==true){
                    asignaturas=asignaturas+"Filosofia-";
                }
                if (check3.isChecked()==true){
                    asignaturas=asignaturas+"Matematicas-";
                }
                if (check4.isChecked()==true){
                    asignaturas=asignaturas+"Informatica-";
                }
                if (check5.isChecked()==true){
                    asignaturas=asignaturas+"Deporte-";
                }
                String dia=sp2.getSelectedItem().toString();
                String mes=sp3.getSelectedItem().toString();
                String anio=sp1.getSelectedItem().toString();
                String fechaTxt=dia+"/"+mes+"/"+anio;
                if (sw1.isChecked()==true){
                    becadoTxt="si";
                }else{
                    becadoTxt="no";
                }
                escribir(usuarioTxt,claveTxt,nombreTxt,apellidoTxt,emailTxt,celularTxt,generoTxt,fechaTxt,asignaturas,becadoTxt);
                Intent intent=new Intent(Registro.this,MainActivity.class);
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
}
