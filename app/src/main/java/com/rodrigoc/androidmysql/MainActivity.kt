package com.rodrigoc.androidmysql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    var txtCoche:EditText?=null
    var txtEmpresa:EditText?=null
    var txtValidador:EditText?=null
    var txtSeriale:EditText?=null
    var txtSerialp:EditText?=null
    var txtFecha:EditText?=null
    var txtObservacion:EditText?=null
    var txtTecnico:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtCoche=findViewById(R.id.txtCoche)
        txtEmpresa=findViewById(R.id.txtEmpresa)
        txtValidador=findViewById(R.id.txtValidador)
        txtSeriale=findViewById(R.id.txtSeriale)
        txtSerialp=findViewById(R.id.txtSerialp)
        txtFecha=findViewById(R.id.txtfecha)
        txtObservacion=findViewById(R.id.txtObservacion)
        txtTecnico=findViewById(R.id.txtTecnico)
    }
    fun clickbnInsertar(view:View){
        val url="http://192.168.1.109/android/insertar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost= object :StringRequest(Request.Method.POST,url,
        Response.Listener<String> { response ->
            Toast.makeText(this,"Datos insertados exitosamente",Toast.LENGTH_LONG).show()
        },Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error",Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()
                parametros.put("coche",txtCoche?.text.toString())
                parametros.put("empresa",txtEmpresa?.text.toString())
                parametros.put("validador",txtValidador?.text.toString())
                parametros.put("seriale",txtSeriale?.text.toString())
                parametros.put("serialp",txtSerialp?.text.toString())
                parametros.put("fecha",txtFecha?.text.toString())
                parametros.put("observacion",txtObservacion?.text.toString())
                parametros.put("tecnico",txtTecnico?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}