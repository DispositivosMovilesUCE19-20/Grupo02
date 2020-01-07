package ec.edu.uce.optativa3.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deberpractica.R;

import java.util.Date;

import ec.edu.uce.optativa3.controlador.DaoLogs;
import ec.edu.uce.optativa3.controlador.DaoUsuario;
import ec.edu.uce.optativa3.modelo.Logs;
import ec.edu.uce.optativa3.modelo.Usuario;

public class RegistroUsuario extends AppCompatActivity {
    private EditText usuario;
    private EditText clave;
    private Button btn1;
    DaoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        usuario = (EditText) findViewById(R.id.editText10);
        clave=(EditText)findViewById(R.id.editText14);
        btn1=(Button) findViewById(R.id.button5);
        dao=new DaoUsuario( RegistroUsuario.this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioTxt = (String) usuario.getText().toString();
                String claveTxt = (String) clave.getText().toString();

                Usuario u=new Usuario();
                u.setEmailUsuario(usuarioTxt);
                u.setContraseñaUsuario(claveTxt);
                if(u.isNull()){
                    Toast.makeText(RegistroUsuario.this,"Error, campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(RegistroUsuario.this,"Registro exitoso",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegistroUsuario.this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                }
                Intent intent=new Intent(RegistroUsuario.this,MainActivity.class);
                //intent.putExtra()
                startActivity(intent);

            }
        });

    }

    public void eliminarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.clear();
        editor.commit();
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
                Intent intent=new Intent(RegistroUsuario.this,MainActivity.class);
                //intent.putExtra()
                startActivity(intent);
                guardarpreferencias("fin");
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
    public void guardarpreferencias(String tipo){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        DaoLogs daoLogs=new DaoLogs(RegistroUsuario.this);
        String user=preferencias.getString( "user","");
        String inicio=tipo;
        String fin=preferencias.getString("fin","");
        String modelo=preferencias.getString("modelo","");
        String androidVersion=preferencias.getString("androidVersion","");
        daoLogs.insertLogs(new Logs(user,inicio,fin,modelo,androidVersion));
        System.out.println("......"+user+" " + inicio+" "+ fin+" "+inicio+" "+modelo+" "+androidVersion+".............");
    }
}
