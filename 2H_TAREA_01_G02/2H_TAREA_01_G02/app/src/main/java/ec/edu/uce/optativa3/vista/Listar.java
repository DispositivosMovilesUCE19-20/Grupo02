package ec.edu.uce.optativa3.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deberpractica.R;

import java.util.ArrayList;

import ec.edu.uce.optativa3.controlador.DaoEstudiante;
import ec.edu.uce.optativa3.controlador.DaoLogs;
import ec.edu.uce.optativa3.controlador.LeerArchivo;
import ec.edu.uce.optativa3.controlador.ListaEstudiantes;
import ec.edu.uce.optativa3.controlador.ObtenerServicio;
import ec.edu.uce.optativa3.modelo.Estudiante;
import ec.edu.uce.optativa3.modelo.Logs;

public class Listar extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;

    private TextView usuariosListar;
    private Spinner sp1;
    private LeerArchivo archivos=new LeerArchivo();
    ArrayList<String> usuariosDetalles;

    private TextView textoservicio;
    int posi ;
    String id1,usuarioi,clavei,nombre,apellido,email,telefono,genero,fecha,asignaturas,becado;

    private ObtenerServicio obtener = new ObtenerServicio();
    Estudiante es;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        textoservicio=(TextView)findViewById(R.id.textView5);
        textoservicio.setText(obtener.getDato(3));

        usuariosListar = (TextView) findViewById(R.id.textView3);
        sp1=(Spinner)findViewById(R.id.spinner4);
        btn1=(Button) findViewById(R.id.button2);
        btn2=(Button) findViewById(R.id.button3);
        btn3=(Button) findViewById(R.id.button6);
        final DaoEstudiante dao=new DaoEstudiante(Listar.this);
        final ArrayList<String>[] usuariosAreglos = new ArrayList[]{dao.leerColumna(1)};
        ArrayList<String> claveAreglos= dao.leerColumna(2);;


        //String [] usuaruiosAreglo ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, usuariosAreglos[0]);
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

                        posi =posicion;
                        usuariosDetalles=dao.selectStringEstudiante();
                        String [] parts = usuariosDetalles.get(posicion).split(" ");
                        //System.out.println(parts.length+"tamaño");
                        if (parts.length==11) {
                            id1 = parts[0];
                            usuarioi = parts[1];
                            clavei = parts[2];
                            nombre = parts[3];
                            apellido = parts[4];
                            email = parts[5];
                            telefono = parts[6];
                            genero = parts[7];
                            fecha = parts[8];
                            asignaturas = parts[9];
                            becado = parts[10];
                            String detallesUsuarioElegido = "nombre: " + nombre + "\n" + "apellido: " + apellido + "\n" + "email: " + email + "\n" + "telefono: " + telefono + "\n" + "genero: " + genero + "\n" + "fecha: " + fecha + "\n" + "asignatura: " + asignaturas + "\n" + "becado: " + becado;
                            usuariosListar.setText(detallesUsuarioElegido);
                            es=dao.getUsuarioID((int)Integer.valueOf(id1));
                            System.out.println("id de estudiante"+id1);
                        }else{
                            Toast.makeText(Listar.this,"ERROR DE ENTRADA DE DATOS",Toast.LENGTH_LONG).show();

                        }
                    }
                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Listar.this, obtener.getDato(2),Toast.LENGTH_LONG).show();
                ArrayList<String> listaestudiantes=dao.selectStringEstudiante();
                SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                String usuarioActuak=preferencias.getString("user","nousuario");
                System.out.println(es.toString());
                if (dao.deleteEstudiante(es.getId())){
                    Toast.makeText(Listar.this,"Eliminado estudiante "+usuarioi,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Listar.this,"Error al eliminar"+usuarioi,Toast.LENGTH_LONG).show();
                }
                Intent intent=new Intent(Listar.this,Listar.class);
                startActivity(intent);
                finish();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Listar.this,"Es"+usuarioi,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Listar.this,Eliminar.class);
                intent.putExtra("id1",id1);
                intent.putExtra("usuarioi",usuarioi);
                intent.putExtra("clavei",clavei);
                intent.putExtra("nombre",nombre);
                intent.putExtra("apellido",apellido);
                intent.putExtra("telefono",telefono);
                intent.putExtra("email",email);
                intent.putExtra("genero",genero);
                intent.putExtra("fecha",fecha);
                intent.putExtra("asignaturas",asignaturas);
                intent.putExtra("becado",becado);

                startActivity(intent);
                //eliminarpreferencias();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Listar.this,Registro.class);
                startActivity(intent);
                finish();
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
    public void eliminarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.clear();
        editor.commit();
    }

    public void guardarpreferencias(String tipo){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        DaoLogs daoLogs=new DaoLogs(Listar.this);
        String user=preferencias.getString( "user","");
        String inicio=tipo;
        String fin=preferencias.getString("fin","");
        String modelo=preferencias.getString("modelo","");
        String androidVersion=preferencias.getString("androidVersion","");
        daoLogs.insertLogs(new Logs(user,inicio,fin,modelo,androidVersion));
        System.out.println("......"+user+" " + inicio+" "+ fin+" "+inicio+" "+modelo+" "+androidVersion+".............");
    }
}

