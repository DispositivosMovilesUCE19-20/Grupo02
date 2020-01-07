package ec.edu.uce.optativa3.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ec.edu.uce.optativa3.modelo.Estudiante;
import ec.edu.uce.optativa3.modelo.Logs;

public class DaoLogs {
    Context c;
    Logs logs;
    ArrayList<Logs> lista;
    SQLiteDatabase sql;
    String bd="Usuarios";
    String table="create table if not exists logs(id integer primary key autoincrement, usuario text, inicio text, fin text, modelo text, androidVersion text)";
    public DaoLogs(Context c) {
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(table);
        logs=new Logs();
    }
    public boolean insertLogs(Logs logs){
        if(buscar(logs.getUsuario())==0 ){
            ContentValues cv= new ContentValues();
            cv.put("usuario",logs.getUsuario());
            cv.put("inicio",logs.getInicio());
            cv.put("fin",logs.getFin());
            cv.put("modelo",logs.getModelo());
            cv.put("androidVersion",logs.getAndroidVersion());
            return (sql.insert("logs",null,cv)>0);
        }else{
            return false;
        }
    }



    public int buscar(String e){
        int x=0;
        lista=selectLogs();
        for(Logs us:lista){
            if(us.getUsuario().equals(e)){
                x++;
            }
        }
        return x;

    }

    public ArrayList<Logs> selectLogs(){
        ArrayList<Logs> lista =new ArrayList<Logs>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from logs",null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Logs e=new Logs();
                e.setId(cr.getInt(0));
                e.setUsuario(cr.getString(1));
                e.setInicio(cr.getString(2));
                e.setFin(cr.getString(3));
                e.setModelo(cr.getString(4));
                e.setAndroidVersion(cr.getString(5));
                lista.add(e);

            }while(cr.moveToNext());
        }
        return lista;
    }
    public boolean updateLogs(Logs e){
        ContentValues cv=new ContentValues();
        cv.put("usuario",e.getUsuario());
        cv.put("fin",e.getFin());
        cv.put("inicio",e.getInicio());
        cv.put("modelo",e.getModelo());
        cv.put("androidVersion",e.getAndroidVersion());
        return (sql.update("estudiante",cv,"id="+e.getId(),null)>0);
    }
    public boolean deleteLogs(int id){
        return (sql.delete("logs","id="+id,null))>0;
    }


    public Logs getLogsID(int id){
        lista=selectLogs();
        for (Logs us:lista){
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }

}
