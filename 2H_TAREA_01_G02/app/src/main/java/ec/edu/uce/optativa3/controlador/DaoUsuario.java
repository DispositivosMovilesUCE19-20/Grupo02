package ec.edu.uce.optativa3.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ec.edu.uce.optativa3.modelo.Usuario;

public class DaoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd="Usuarios";
    String table="create table if not exists usuario(id integer primary key autoincrement, emailUsuario text, contrasenaUsuario text)";

    public DaoUsuario(Context c) {
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(table);
        u=new Usuario();

    }
    public boolean insertUsuario(Usuario u){
        if(buscar(u.getEmailUsuario())==0 ){
            ContentValues cv= new ContentValues();
            cv.put("emailUsuario",u.getEmailUsuario());
            cv.put("contrasenaUsuario",u.getContraseñaUsuario());
            return (sql.insert("usuario",null,cv)>0);

        }else{
            return false;
        }
    }
    public int buscar(String u){
        int x=0;
        lista=selectUsuario();
        for(Usuario us:lista){
            if(us.getEmailUsuario().equals(u)){
                x++;

            }
        }
        return x;

    }
    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista =new ArrayList<Usuario>();
                lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario",null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Usuario u=new Usuario();
                u.setId(cr.getInt(0));
                u.setEmailUsuario(cr.getString(1));
                u.setContraseñaUsuario(cr.getString(2));
                lista.add(u);

            }while(cr.moveToNext());
        }
    return lista;
    }
    public int login(String u,String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());

        }
        return a;
    }
    public Usuario getUsuario(String u,String p){
        lista=selectUsuario();
        for (Usuario us:lista){
            if(us.getEmailUsuario().equals(u)&&us.getContraseñaUsuario().equals(p)){
                return us;
            }
        }
        return null;
    }
    public Usuario getUsuarioID(int id){
        lista=selectUsuario();
        for (Usuario us:lista){
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }
}
