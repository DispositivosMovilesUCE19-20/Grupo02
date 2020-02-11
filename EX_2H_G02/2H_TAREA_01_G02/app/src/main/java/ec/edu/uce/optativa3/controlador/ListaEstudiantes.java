package ec.edu.uce.optativa3.controlador;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.optativa3.modelo.Estudiante;

public class ListaEstudiantes {
    Estudiante u;
    List<Estudiante> lista;

    private String archivo = "Json";
    private String carpeta = "/Download/";
    File file;
    String file_path = "";
    String name = "";
    LeerArchivo arch=new LeerArchivo();

    public ListaEstudiantes() {
        lista=arch.cambiarEstudiante();
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
