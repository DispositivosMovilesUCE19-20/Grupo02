package ec.edu.uce.optativa3.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deberpractica.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ec.edu.uce.optativa3.controlador.DaoEstudiante;
import ec.edu.uce.optativa3.controlador.DaoLogs;
import ec.edu.uce.optativa3.controlador.LeerArchivo;
import ec.edu.uce.optativa3.controlador.ListaEstudiantes;
import ec.edu.uce.optativa3.controlador.ObtenerServicio;
import ec.edu.uce.optativa3.modelo.Estudiante;
import ec.edu.uce.optativa3.modelo.Logs;

public class Eliminar extends AppCompatActivity {
Bundle datos;
    //private EditText usuario;
    //private EditText clave;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText celular;
    private RadioButton rb1,rb2;
    private CheckBox check1,check2,check3,check4,check5;
    private Spinner sp1,sp2,sp3;
    private Switch sw1;
    private Button btn1;
    private LeerArchivo archivos=new LeerArchivo();
    private ListaEstudiantes arch=new ListaEstudiantes();
    private TextView textoservicio;

    ObtenerServicio mensaje =new ObtenerServicio() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        datos= getIntent().getExtras();
        String nombrestr=datos.getString("nombre");
        String apellidostr=datos.getString("apellido");
        String telefonostr=datos.getString("telefono");
        final String emailstr=datos.getString("email");
        String generostr=datos.getString("genero");
        String fechastr=datos.getString("fecha");
        String asignaturasstr=datos.getString("asignaturas");
        String becadostr=datos.getString("becado");
        final String usuarioistr=datos.getString("usuarioi");
        final String claveistr=datos.getString("clavei");
        final int pos=datos.getInt("posi");
        final DaoEstudiante dao=new DaoEstudiante(Eliminar.this);
        //usuario = (EditText) findViewById(R.id.editText5);
        //clave=(EditText)findViewById(R.id.editText6);
        nombre = (EditText) findViewById(R.id.editText9);
        apellido=(EditText)findViewById(R.id.editText11);
        email = (EditText) findViewById(R.id.editText13);
        celular=(EditText)findViewById(R.id.editText12);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        check1=(CheckBox)findViewById(R.id.checkBox7);
        check2=(CheckBox)findViewById(R.id.checkBox6);
        check3=(CheckBox)findViewById(R.id.checkBox10);
        check4=(CheckBox)findViewById(R.id.checkBox9);
        check5=(CheckBox)findViewById(R.id.checkBox8);
        sp1=(Spinner)findViewById(R.id.spinner7);
        sp2=(Spinner)findViewById(R.id.spinner8);
        sp3=(Spinner)findViewById(R.id.spinner5);
        sw1=(Switch)findViewById(R.id.switch2);
        btn1=(Button) findViewById(R.id.button4);
        textoservicio=(TextView)  findViewById(R.id.textView6);
       // textoservicio.setText(mensaje.getDato(3));
        String [] parts = fechastr.split("/");
        //System.out.println(parts.length+"tamaño");
        String diastr="";
        String messtr="";
        String aniostr="";
        if (parts.length==3) {
            diastr = parts[0];
            messtr = parts[1];
            aniostr = parts[2];
        }else{
            Toast.makeText(Eliminar.this,"ERROR DE ENTRADA DE DATOS",Toast.LENGTH_LONG).show();

        }
        String [] dia ={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        ArrayAdapter <String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dia);
        sp1.setAdapter(adapter);
        List<String> listadia = Arrays.asList(dia);
        sp1.setSelection(listadia.indexOf(diastr));

        String [] mes ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter <String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mes);
        sp2.setAdapter(adapter1);
        List<String> listames = Arrays.asList(mes);
        sp2.setSelection(listames.indexOf(messtr));

        String [] anio ={"1981","1982","1983","1984","1985","1986","1987","1988","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009"};
        ArrayAdapter <String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,anio);
        sp3.setAdapter(adapter2);
        List<String> listaanio = Arrays.asList(anio);
        sp3.setSelection(listaanio.indexOf(aniostr));

        nombre.setText(nombrestr);
        apellido.setText(apellidostr);
        email.setText(emailstr);
        celular.setText(telefonostr);
        if (generostr.equals("hombre")){
            rb1.toggle();
        }else{
            rb2.toggle();
        }
        String [] parts2 = asignaturasstr.split("-");
        //System.out.println(parts.length+"tamaño");
        if (parts2.length>=3) {
            for (int i=0;i<parts2.length;i++){
                String asig = parts2[i];
                switch (asig) {
                    case "Filosofia":
                        check1.setChecked(true);
                        break;
                    case "Deporte":
                        check2.setChecked(true);
                        break;
                    case "Informatica":
                        check3.setChecked(true);
                        break;
                    case "Matematicas":
                        check4.setChecked(true);
                        break;
                    case "Ciencias":
                        check5.setChecked(true);
                        break;
                }
            }
        }else{
            Toast.makeText(Eliminar.this,"ERROR DE ENTRADA DE DATOS",Toast.LENGTH_LONG).show();

        }
        if(becadostr.equals("si")){
            sw1.setChecked(true);
        }else{
            sw1.setChecked(false);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String usuarioTxt = (String) usuario.getText().toString();
                //String claveTxt = (String) clave.getText().toString();
                String nombreTxt = (String) nombre.getText().toString();
                String apellidoTxt = (String) apellido.getText().toString();
                String emailTxt = (String) email.getText().toString();
                String celularTxt = (String) celular.getText().toString();
                String generoTxt;
                String becadoTxt;
                String asignaturas = "";
                if (rb1.isChecked() == true) {
                    generoTxt = "hombre";
                } else {
                    generoTxt = "mujer";
                }
                int contadorCheck = 0;
                if (check1.isChecked() == true) {
                    asignaturas = asignaturas + "Filosofia-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check2.isChecked() == true) {
                    asignaturas = asignaturas + "Deporte-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check3.isChecked() == true) {
                    asignaturas = asignaturas + "Informatica-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check4.isChecked() == true) {
                    asignaturas = asignaturas + "Matematicas-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check5.isChecked() == true) {
                    asignaturas = asignaturas + "Ciencias-";
                    contadorCheck = contadorCheck + 1;
                }
                String dia = sp1.getSelectedItem().toString();
                String mes = sp2.getSelectedItem().toString();
                String anio = sp3.getSelectedItem().toString();
                String fechaTxt = dia + "/" + mes + "/" + anio;
                if (sw1.isChecked() == true) {
                    becadoTxt = "si";
                } else {
                    becadoTxt = "no";
                }
                if ( (contadorCheck >= 3)&& validarEntrada(nombreTxt) && validarEntrada(apellidoTxt) && validarEntrada(emailTxt) && validarEntrada(celularTxt)&&validarEmail(emailTxt)) {
                    String modificado=usuarioistr+ " " + claveistr + " " + nombreTxt + " " + apellidoTxt + " "
                            + emailTxt + " " + celularTxt + " " + generoTxt+ " " + fechaTxt + " " + asignaturas + " " + becadoTxt;

                    Estudiante es=dao.getEstudiante(usuarioistr,claveistr);
                    es.setNombre(nombreTxt);
                    es.setApellido(apellidoTxt);
                    es.setEmail(emailTxt);
                    es.setCelular(celularTxt);
                    es.setGenero(generoTxt);
                    es.setFechaNacimiento(fechaTxt);
                    es.setAsignatura(asignaturas);
                    es.setBecado(becadoTxt);
                    if(dao.updateEstudiante(es)){
                        Toast.makeText(Eliminar.this, "Editado correctamente "+ usuarioistr, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Eliminar.this, "Error al editar", Toast.LENGTH_SHORT).show();
                    }


                    Toast.makeText(Eliminar.this, mensaje.getDato(2), Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(Eliminar.this, MainActivity.class);
                            //intent.putExtra()
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);

                } else if (contadorCheck < 3) {
                    Toast.makeText(Eliminar.this, "Elija al menos 3 asignaturas", Toast.LENGTH_LONG).show();

                }else if(!validarEmail(emailTxt)){
                    Toast.makeText(Eliminar.this, "no se admite email", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Eliminar.this, "no se admite caracter espacio", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public boolean validarEntrada(String str){
        if (str.indexOf(" ")<0){
            return true;
        }else{
            return false;
        }
    }

    public boolean validarEmail(String str){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(str);

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu miMenu){
        getMenuInflater().inflate(R.menu.menu, miMenu);
        return true;
    }

    public void eliminarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                Intent intent=new Intent(Eliminar.this,MainActivity.class);
                //intent.putExtra()
                startActivity(intent);
                guardarpreferencias("fin");
                eliminarpreferencias();
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
        DaoLogs daoLogs=new DaoLogs(Eliminar.this);
        String user=preferencias.getString( "user","");
        String inicio=tipo;
        String fin=preferencias.getString("fin","");
        String modelo=preferencias.getString("modelo","");
        String androidVersion=preferencias.getString("androidVersion","");
        daoLogs.insertLogs(new Logs(user,inicio,fin,modelo,androidVersion));
        System.out.println("......"+user+" " + inicio+" "+ fin+" "+inicio+" "+modelo+" "+androidVersion+".............");
    }
}

