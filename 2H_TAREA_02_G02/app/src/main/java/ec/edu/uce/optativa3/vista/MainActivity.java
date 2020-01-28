package ec.edu.uce.optativa3.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deberpractica.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import ec.edu.uce.optativa3.controlador.DaoLogs;
import ec.edu.uce.optativa3.controlador.DaoUsuario;
import ec.edu.uce.optativa3.controlador.LeerArchivo;
import ec.edu.uce.optativa3.controlador.ListaEstudiantes;
import ec.edu.uce.optativa3.controlador.ObtenerServicio;
import ec.edu.uce.optativa3.modelo.Logs;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private EditText usuarioTextView;
    private EditText claveTextView;
    String usuarioTxt;
    String claveTxt;
    private TextView textoservicio;
    private ObtenerServicio obtener = new ObtenerServicio();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView textviewsensor;
    SensorManager sensorManager;
    Sensor sensor1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) findViewById(R.id.buttonIngreso);
        btn2=(Button) findViewById(R.id.buttonRegistro);
        btn3=(Button) findViewById(R.id.button7);
        textviewsensor=(TextView)findViewById(R.id.textView7);
        usuarioTextView = (EditText) findViewById(R.id.editText);//texto de ingreso de usuario
        claveTextView=(EditText)findViewById(R.id.editText2);//texto de ingreso de clave
        textoservicio=(TextView)findViewById(R.id.textView);
        sensorManager = (SensorManager)getSystemService(Service.SENSOR_SERVICE) ;
        sensor1=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
       // textoservicio.setText(obtener.getDato(3));
        //obtener.RealizarPost(this);
        cargarpreferencias();
        inicializarFirebase();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioTxt= (String) usuarioTextView.getText().toString();
                claveTxt= (String) claveTextView.getText().toString();
                DaoUsuario dao=new DaoUsuario(MainActivity.this);
                if (dao.login(usuarioTxt,claveTxt)>0) {
//                    Toast.makeText(MainActivity.this, obtener.servicioExamen(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,"Usuario y clave correcto",Toast.LENGTH_LONG).show();
                   // Handler handler = new Handler();
                    //handler.postDelayed(new Runnable() {
                        //public void run() {
                            Intent intent=new Intent(MainActivity.this,Listar.class);
                            //intent.putExtra()
                            startActivity(intent);
                            guardarpreferencias("inicio");
                            finish();
                     //   }
                    //}, 200);
                } else{

                            Toast.makeText(MainActivity.this,"Credenciales incorectas",Toast.LENGTH_LONG).show();
                        }

                    }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegistroUsuario.class);
                //intent.putExtra()
                startActivity(intent);
                //eliminarpreferencias();
                cargarpreferencias();
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarLogsBDCentral();
            }
        });
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
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                //intent.putExtra()
                startActivity(intent);
                eliminarpreferencias();
                cargarpreferencias();
                finish();
                return true;
            case R.id.salir:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void cargarpreferencias(){
    SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
    String user=preferencias.getString( "user","");
    String pass=preferencias.getString("pass","");
    String inicio=preferencias.getString( "inicio","");
    String fin=preferencias.getString("fin","");
    String modelo=preferencias.getString("modelo","");
    String androidVersion=preferencias.getString("androidVersion","");
    usuarioTextView.setText(user);
    claveTextView.setText(pass);
}

    public void guardarpreferencias(String tipo){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user= usuarioTextView.getText().toString();
        String pass= claveTextView.getText().toString();
        String inicio=tipo;
        String fin= new Date(Build.TIME).toString();
        String modelo=android.os.Build.DEVICE;
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        String androidVersion="Android SDK: " + sdkVersion + " (" + release +")";;
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("user",user);
        editor.putString("pass",pass);
        editor.putString("inicio",inicio);
        editor.putString("fin",fin);
        editor.putString("modelo",modelo);
        editor.putString("androidVersion",androidVersion);
        editor.commit();
        DaoLogs daoLogs=new DaoLogs(MainActivity.this);
        daoLogs.insertLogs(new Logs(user,inicio,fin,modelo,androidVersion));
        System.out.println("......"+user+" " + inicio+" "+ fin+" "+inicio+" "+modelo+" "+androidVersion+".............");
    }
    public void eliminarpreferencias(){
        SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.clear();
        editor.commit();
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
    public void guardarLogsBDCentral(){
        DaoLogs daoLogs=new DaoLogs(MainActivity.this);
        ArrayList<Logs>lista=daoLogs.selectLogs();
        for(Logs us:lista){
            databaseReference.child("Logs").child(UUID.randomUUID().toString()).setValue(us);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor1 ,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            textviewsensor.setText(""+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}



