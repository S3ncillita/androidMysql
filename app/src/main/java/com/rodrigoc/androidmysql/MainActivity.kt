package com.rodrigoc.androidmysql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isEmpty
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    var txtCoche:EditText?=null
    var txtempresa:Spinner?=null
    var txtValidador:Spinner?=null
    var txtSeriale:EditText?=null
    var txtSerialp:EditText?=null
    var txtFecha:EditText?=null
    var txtObservacion:EditText?=null
    var txtTecnico:Spinner?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCoche=findViewById(R.id.txtCoche)
        txtempresa=findViewById(R.id.txtempresa)
        txtValidador=findViewById(R.id.txtValidador)
        txtSeriale=findViewById(R.id.txtSeriale)
        txtSerialp=findViewById(R.id.txtSerialp)
        txtFecha=findViewById(R.id.txtFecha)
        txtObservacion=findViewById(R.id.txtObservacion)
        txtTecnico=findViewById(R.id.txtTecnico)

    }
    @OptIn(ExperimentalStdlibApi::class)
    fun clickbnInsertar(view:View) {

        val url = "http://192.168.1.109/android/insertar.php"
        val queue = Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Datos insertados exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): HashMap<String, String> {

                val parametros = HashMap<String, String>()
                parametros.put("coche", txtCoche?.text.toString())
                parametros.put("empresa", txtempresa?.selectedItem.toString())
                parametros.put("validador", txtValidador?.selectedItem.toString())
                parametros.put("seriale", txtSeriale?.text.toString())
                parametros.put("serialp", txtSerialp?.text.toString())
                parametros.put("fecha", txtFecha?.text.toString())
                parametros.put("observacion", txtObservacion?.text.toString())
                parametros.put("tecnico", txtTecnico?.selectedItem.toString())
                return parametros


            }

        }

        var SELECCIONA = null
        if(txtCoche!!.length()==0)
        {
            Toast.makeText(this,"Completa el campo de Coche",Toast.LENGTH_LONG).show()
        }
        else if (txtFecha!!.length()==0)
        {
            Toast.makeText(this,"Agregue la Fecha",Toast.LENGTH_LONG).show()
        }
        else if(txtObservacion!!.length()==0)
        {
            Toast.makeText(this,"Describa el detalle",Toast.LENGTH_LONG).show()
        }
        else {
            queue.add(resultadoPost)
        }

    }
}