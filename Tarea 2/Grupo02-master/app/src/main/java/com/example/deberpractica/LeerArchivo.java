package com.example.deberpractica;

import android.os.Environment;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LeerArchivo {
    private String archivo = "miarchivo9";
    private String carpeta = "/Download/";
    File file;
    String file_path = "";
    String name = "";

    private String archivo1 = "Json";
    private String carpeta1 = "/Download/";
    File file1;
    String file_path1 = "";
    String name1 = "";

    public LeerArchivo() {

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
                escribir("admin", "admin", "david", "lara", "dllara@uce.edu.ec", "0998124155", "hombre", "23/10/1995", "Fisica-Ciencias-Informatica-", "si");
            }
            } catch (
                IOException e) {
            e.printStackTrace();
        }
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
    public  ArrayList<String> leer(){
        FileInputStream fin=null;
        //String matriz[] = new String[1000];
        ArrayList<String> matriz = new ArrayList<String>();
        int n=0;
        try{
            fin= new FileInputStream(file);

        }catch(FileNotFoundException e1){
            e1.printStackTrace();
        }
        InputStreamReader archivo=new InputStreamReader(fin);
        BufferedReader br=new BufferedReader(archivo);
        try{
            String linea;
            //int i = 0;
            while ((linea = br.readLine()) != null) {
                //matriz[i] = linea;
                matriz.add(linea);
                //i=i+1;
            }
            n=matriz.size()+1;


        }catch (IOException e){
            e.printStackTrace();
        }

        return matriz;
    }
    public  ArrayList<String> leerColumna(int j){
        ArrayList<String> usuariosAreglos= new ArrayList<String>();
        //ArrayList<String> claveAreglos= new ArrayList<String>();
        ArrayList<String> usuariosDetalles =this.leer();
        for (int i = 0; i < usuariosDetalles.size(); i++) {
            System.out.println(usuariosDetalles.get(i)+"leer");
            String [] parts = usuariosDetalles.get(i).split(" ");
            System.out.println(parts.length+"tamaño");
            String usuarioi = parts[j];
            System.out.println(usuarioi+" vector usuario");
            //String clavei = parts[1];
            //System.out.println(usuarioi + " - " + clavei);
            usuariosAreglos.add(usuarioi);
            //claveAreglos.add(clavei);

            // }
        }
        return usuariosAreglos;
    }

    public ArrayList<Estudiante> cambiarEstudiante() {
        ArrayList<String> strEstudiantes= this.leer();
        ArrayList<Estudiante> arrayEstudiantes=new ArrayList<>();
        for(int i=0;i<strEstudiantes.size();i++) {
            String[] parts = strEstudiantes.get(i).split(" ");
            //System.out.println(parts.length+"tamaño");
            if (parts.length == 10) {
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
                String[] partsAsig = asignaturas.split("-");
                ArrayList<String>arAsig=new ArrayList<String>();
                for(int j=0;j<partsAsig.length;j++){
                    arAsig.add(partsAsig[j]);
                }
                arrayEstudiantes.add(new Estudiante(usuarioi,clavei,nombre,apellido,email,telefono,"path foto",genero,fecha,arAsig,becado));
            } else {
                //Toast.makeText(Listar, "ERROR DE ENTRADA DE DATOS", Toast.LENGTH_LONG).show();
                System.out.println("error de entrada");

            }
        }
        return arrayEstudiantes;
    }
    public String estudianteToJson(){
        ArrayList<String> listasStrEstudiantes=this.leer();
        ArrayList<Estudiante> listasEstudiantes=this.cambiarEstudiante();
        Gson gson = new Gson();
        //String arrayData = gson.toJson(listasEstudiantes);
       String arrayDataEstudiante=gson.toJson(listasEstudiantes);
        System.out.println(arrayDataEstudiante);

        return arrayDataEstudiante;
    }
    public void LeerEstudiantes() {


    }
    public void escribirEstudiantes(){

        this.file_path1 = (Environment.getExternalStorageDirectory() + this.carpeta1);
        //this.file_path = (Environment.DIRECTORY_DOWNLOADS + this.carpeta);
        File localFile = new File(this.file_path1);
        if (!localFile.exists()) {
            localFile.mkdir();
        }
        this.name = (this.archivo1 + ".txt");
        this.file1 = new File(localFile, this.name1);
        try {
            this.file1.createNewFile();
            if(!file1.exists()) {
               // escribir("admin", "admin", "david", "lara", "dllara@uce.edu.ec", "0998124155", "hombre", "23/10/1995", "Fisica-Ciencias-Informatica-", "si");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        FileWriter fichero1=null;
        PrintWriter pw =null;
        try{
            //fichero1=new FileWriter(file1);
            fichero1=new FileWriter(file1.getAbsoluteFile(),true);
            pw=new PrintWriter(fichero1);
            //pw.println(usuarioTextView.getText().toString());
            pw.println(this.estudianteToJson());
            pw.flush();
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (null!= fichero1)
                    fichero1.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}

