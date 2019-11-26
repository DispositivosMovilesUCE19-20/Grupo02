package ec.edu.uce.optativa3.controlador;

import android.content.Context;
import android.os.StrictMode;
import android.util.JsonReader;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ObtenerServicio {
    String sql = null;
    public String getDato(int dato){
        switch (dato){
            case 1:
                 sql = "http://2297c551.ngrok.io/oper/1";
                break;
            case 2:
                 sql = "http://2297c551.ngrok.io/oper/2";
                break;
            case 3:
                 sql = "http://2297c551.ngrok.io/";
                break;
            default:
                 sql = "http://2297c551.ngrok.io/oper/3";
                break;
    }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = null;
        String json = "";
        HttpURLConnection conn;
        JsonReader reader = null;
        Gson gson = new Gson();
        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            json = response.toString();
            Mensaje msg  = new Gson().fromJson(json, Mensaje.class);
            return msg.getMsg();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "test prueba1";
        } catch (IOException e) {
            e.printStackTrace();
            return "test prueba2";
        }
    }

    class Mensaje{
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public void RealizarPost(Context activity) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String URL = "http://e7474e2f.ngrok.io/escribir";
        final String[] respuesta = {"test prueba3"};
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        respuesta[0] = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("msg", "Pepito Grillo");
                return params;
            }
        };
        //Volley.newRequestQueue(activity).add(postRequest);
        queue.add(postRequest);
    }
    public String servicioExamen(){
        sql="http://2297c551.ngrok.io/servicioExamen";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = null;
        String json = "";
        HttpURLConnection conn;
        JsonReader reader = null;
        Gson gson = new Gson();
        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            json = response.toString();
            Mensaje msg  = new Gson().fromJson(json, Mensaje.class);
            return msg.getMsg();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "test prueba1";
        } catch (IOException e) {
            e.printStackTrace();
            return "test prueba2";
        }
    }

}
