package ec.edu.uce.optativa3.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ec.edu.uce.optativa3.modelo.Estudiante;
import ec.edu.uce.optativa3.modelo.Usuario;

public class DaoEstudiante {
    Context c;
    Estudiante e;
    ArrayList<Estudiante> lista;
    SQLiteDatabase sql;
    String bd="Usuarios";
    String table="create table if not exists estudiante(id integer primary key autoincrement, usuario text, clave text, nombre text, apellido text, email text, celular text, foto text, genero text, fechaNacimiento text, becado text, asignatura text)";
    public DaoEstudiante(Context c) {
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(table);
        e=new Estudiante();
    }
    public boolean insertEstudiante(Estudiante e){
        if(buscar(e.getUsuario())==0 ){
            ContentValues cv= new ContentValues();
            cv.put("usuario",e.getUsuario());
            cv.put("clave",e.getClave());
            cv.put("nombre",e.getNombre());
            cv.put("apellido",e.getApellido());
            cv.put("email",e.getEmail());
            cv.put("celular",e.getCelular());
            cv.put("genero",e.getGenero());
            cv.put("fechaNacimiento",e.getFechaNacimiento());
            cv.put("becado",e.getBecado());
            cv.put("foto",e.getFoto());
            cv.put("asignatura",e.getAsignatura());
            return (sql.insert("estudiante",null,cv)>0);
        }else{
            return false;
        }
    }



    public int buscar(String e){
        int x=0;
        lista=selectEstudiante();
        for(Estudiante us:lista){
            if(us.getUsuario().equals(e)){
                x++;
            }
        }
        return x;

    }
    public ArrayList<String> selectStringEstudiante(){
        ArrayList<String> lista =new ArrayList<String>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from estudiante",null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Estudiante e=new Estudiante();
                e.setId(cr.getInt(0));
                e.setUsuario(cr.getString(1));
                e.setClave(cr.getString(2));
                e.setNombre(cr.getString(3));
                e.setApellido(cr.getString(4));
                e.setEmail(cr.getString(5));
                e.setCelular(cr.getString(6));
                e.setFoto(cr.getString(7));
                e.setGenero(cr.getString(8));
                e.setFechaNacimiento(cr.getString(9));
                e.setBecado(cr.getString(10));
                e.setAsignatura(cr.getString(11));
                lista.add(e.toString());

            }while(cr.moveToNext());
        }
        return lista;
    }
    public ArrayList<Estudiante> selectEstudiante(){
        ArrayList<Estudiante> lista =new ArrayList<Estudiante>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from estudiante",null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Estudiante e=new Estudiante();
                e.setId(cr.getInt(0));
                e.setUsuario(cr.getString(1));
                e.setClave(cr.getString(2));
                e.setNombre(cr.getString(3));
                e.setApellido(cr.getString(4));
                e.setEmail(cr.getString(5));
                e.setCelular(cr.getString(6));
                e.setFoto(cr.getString(7));
                e.setGenero(cr.getString(8));
                e.setFechaNacimiento(cr.getString(9));
                e.setBecado(cr.getString(10));
                e.setAsignatura(cr.getString(11));
                lista.add(e);

            }while(cr.moveToNext());
        }
        return lista;
    }
    public boolean updateEstudiante(Estudiante e){
        ContentValues cv=new ContentValues();
        cv.put("usuario",e.getUsuario());
        cv.put("clave",e.getClave());
        cv.put("nombre",e.getNombre());
        cv.put("apellido",e.getApellido());
        cv.put("email",e.getEmail());
        cv.put("celular",e.getCelular());
        cv.put("genero",e.getGenero());
        cv.put("fechaNacimiento",e.getFechaNacimiento());
        cv.put("becado",e.getBecado());
        cv.put("foto",e.getFoto());
        cv.put("asignatura",e.getAsignatura());
        return (sql.update("estudiante",cv,"id="+e.getId(),null)>0);
    }
    public boolean deleteEstudiante(int id){
        return (sql.delete("estudiante","id="+id,null))>0;
    }
    public int login(String u,String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from estudiante", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());

        }
        return a;
    }
    public Estudiante getEstudiante(String u,String p){
        lista=selectEstudiante();
        for (Estudiante us:lista){
            if(us.getUsuario().equals(u)&&us.getClave().equals(p)){
                return us;
            }
        }
        return null;
    }
    public Estudiante getUsuarioID(int id){
        lista=selectEstudiante();
        for (Estudiante us:lista){
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }
    public  ArrayList<String> leerColumna(int j){
        ArrayList<String> usuariosAreglos= new ArrayList<String>();
        ArrayList<String> usuariosDetalles =this.selectStringEstudiante();
        for (int i = 0; i < usuariosDetalles.size(); i++) {
            System.out.println(usuariosDetalles.get(i)+"leer");
            String [] parts = usuariosDetalles.get(i).split(" ");
            System.out.println(parts.length+"tamaño");
            String usuarioi = parts[j];
            System.out.println(usuarioi+" vector usuario");
            usuariosAreglos.add(usuarioi);
        }
        return usuariosAreglos;
    }
}
