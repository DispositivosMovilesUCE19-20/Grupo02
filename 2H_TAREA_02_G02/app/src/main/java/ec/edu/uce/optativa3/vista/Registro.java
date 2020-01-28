package ec.edu.uce.optativa3.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deberpractica.R;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ec.edu.uce.optativa3.controlador.DaoEstudiante;
import ec.edu.uce.optativa3.controlador.DaoLogs;
import ec.edu.uce.optativa3.controlador.LeerArchivo;
import ec.edu.uce.optativa3.controlador.ListaEstudiantes;
import ec.edu.uce.optativa3.controlador.ObtenerServicio;
import ec.edu.uce.optativa3.modelo.Estudiante;
import ec.edu.uce.optativa3.modelo.Logs;

public class    Registro extends AppCompatActivity {
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
    private LeerArchivo archivos=new LeerArchivo();
    private ListaEstudiantes arch=new ListaEstudiantes();
    private TextView textoservicio;
    ImageView   imagen;

    private ObtenerServicio obtener = new ObtenerServicio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final DaoEstudiante dao=new DaoEstudiante(Registro.this);
        usuario = (EditText) findViewById(R.id.editText5);
        clave=(EditText)findViewById(R.id.editText6);
        nombre = (EditText) findViewById(R.id.editText7);
        apellido=(EditText)findViewById(R.id.editText8);
        email = (EditText) findViewById(R.id.editText3);
        celular=(EditText)findViewById(R.id.editText4);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
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
        textoservicio=(TextView)findViewById(R.id.textView4);
        imagen =(ImageView)findViewById(R.id.imageView6);
        textoservicio.setText(obtener.getDato(3));
        String [] dia ={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15}","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        ArrayAdapter <String> adapter=new ArrayAdapter <String> (this,android.R.layout.simple_list_item_1,dia);
        sp3.setAdapter(adapter);
        String [] mes ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter <String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mes);
        sp1.setAdapter(adapter1);
        String [] anio ={"1981","1982","1983","1984","1985","1986","1987","1988","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009"};
        ArrayAdapter <String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,anio);
        sp2.setAdapter(adapter2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioTxt = (String) usuario.getText().toString();
                String claveTxt = (String) clave.getText().toString();
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
                    asignaturas = asignaturas + "Ciencias-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check2.isChecked() == true) {
                    asignaturas = asignaturas + "Filosofia-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check3.isChecked() == true) {
                    asignaturas = asignaturas + "Matematicas-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check4.isChecked() == true) {
                    asignaturas = asignaturas + "Informatica-";
                    contadorCheck = contadorCheck + 1;
                }
                if (check5.isChecked() == true) {
                    asignaturas = asignaturas + "Deporte-";
                    contadorCheck = contadorCheck + 1;
                }
                String dia = sp3.getSelectedItem().toString();
                String mes = sp1.getSelectedItem().toString();
                String anio = sp2.getSelectedItem().toString();
                String fechaTxt = dia + "/" + mes + "/" + anio;
                if (sw1.isChecked() == true) {
                    becadoTxt = "si";
                } else {
                    becadoTxt = "no";
                }
                if ((!dao.leerColumna(1).contains(usuarioTxt)) && (contadorCheck >= 3)&&!usuarioTxt.isEmpty() && !claveTxt.isEmpty() && validarEntrada(usuarioTxt) && validarEntrada(claveTxt) && validarEntrada(nombreTxt) && validarEntrada(apellidoTxt) && validarEntrada(emailTxt) && validarEntrada(celularTxt)&&validarEmail(emailTxt)&&validarConytraseña(claveTxt))
                {
                        dao.insertEstudiante(new Estudiante(usuarioTxt, claveTxt, nombreTxt, apellidoTxt, emailTxt, celularTxt, generoTxt,fechaTxt, asignaturas, becadoTxt));
                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        //intent.putExtra()
                        startActivity(intent);
                        finish();

                    } else if (dao.leerColumna(1).contains(usuarioTxt)) {
                            Toast.makeText(Registro.this, "Nombre de usuario ya existe", Toast.LENGTH_LONG).show();

                        } else if (contadorCheck < 3) {
                            Toast.makeText(Registro.this, "Esliga al menos 3 asignaturas", Toast.LENGTH_LONG).show();

                        } else if (usuarioTxt.isEmpty()||claveTxt.isEmpty()) {
                            Toast.makeText(Registro.this, "datos vacios", Toast.LENGTH_LONG).show();

                        }else if(!validarEmail(emailTxt)){
                            Toast.makeText(Registro.this, "no se admite email", Toast.LENGTH_LONG).show();
                        }else if(!validarConytraseña(claveTxt)){
                            Toast.makeText(Registro.this, "no se admite contraseña debe tener almenos 8 longitud y caracteres especiales", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Registro.this, "no se admite caracteres en espacio", Toast.LENGTH_LONG).show();
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
    public boolean validarConytraseña(String str){

       //La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula, al menos una mayúscula y al menos un caracter no alfanumérico
        Pattern pattern = Pattern
                .compile("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$");

        Matcher mather = pattern.matcher(str);

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
    public boolean onCreateOptionsMenu(Menu miMenu){
        getMenuInflater().inflate(R.menu.menu, miMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                Intent intent=new Intent(Registro.this,MainActivity.class);
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
    public void eliminarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.clear();
        editor.commit();
    }

    public void onclick(View vista){
        cargarImagen();
    }
    public void cargarImagen(){
         Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/");
            startActivityForResult(intent.createChooser(intent, "SELECCIONA LA APLICACION"),10);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,requestCode,data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            imagen.setImageURI(path);
        }
    }
    public void guardarpreferencias(String tipo){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        DaoLogs daoLogs=new DaoLogs(Registro.this);
        String user=preferencias.getString( "user","");
        String inicio=tipo;
        String fin=preferencias.getString("fin","");
        String modelo=preferencias.getString("modelo","");
        String androidVersion=preferencias.getString("androidVersion","");
        daoLogs.insertLogs(new Logs(user,inicio,fin,modelo,androidVersion));
        System.out.println("......"+user+" " + inicio+" "+ fin+" "+inicio+" "+modelo+" "+androidVersion+".............");
    }

}
