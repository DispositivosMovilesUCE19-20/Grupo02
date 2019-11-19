package com.example.deberpractica;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ListaEstudiantes {
    Estudiante u;
    ArrayList<Estudiante> lista;

    private String archivo = "Json";
    private String carpeta = "/Download/";
    File file;
    String file_path = "";
    String name = "";
    LeerArchivo arch=new LeerArchivo();

    public ListaEstudiantes() {

        this.archivo = archivo;
        this.file_path = (Environment.getExternalStorageDirectory() + this.carpeta);
        //this.file_path = (Environment.DIRECTORY_DOWNLOADS + this.carpeta);
        File localFile = new File(this.file_path);
        if (!localFile.exists()) {
            localFile.mkdir();
        }
        this.name = (this.archivo + ".txt");
        this.file = new

                File(localFile, this.name);
        try {
            this.file.createNewFile();
            if(!file.exists()) {
                this.escribir();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public void escribir( ){
        FileWriter fichero=null;
        PrintWriter pw =null;
        try{
            fichero=new FileWriter(file);
            //fichero=new FileWriter(file.getAbsoluteFile(), true);
            pw=new PrintWriter(fichero);
            //pw.println(usuarioTextView.getText().toString());
            pw.println(arch.estudianteToJson());
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
