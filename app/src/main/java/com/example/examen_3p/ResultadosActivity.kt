package com.example.examen_3p

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ResultadosActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val bundle = intent.extras

        val datoAlumno = bundle?.getString("AlumnoN")
        val datoMateria = bundle?.getString("MateriaN")
        val datoPrimerP = bundle?.getString("primerP")
        val datoSegundoP = bundle?.getString("segundoP")

        val nombre = findViewById<EditText>(R.id.txtResultadoAlumno)
        val materia = findViewById<EditText>(R.id.txtResultadoMateria)
        val primerp = findViewById<EditText>(R.id.txtResultadoPrimerP)
        val segundop = findViewById<EditText>(R.id.txtResultadoSegundoP)
        val btnatras = findViewById<Button>(R.id.btnInicio)

        operacion()
        nombre.setText(datoAlumno.toString())
        materia.setText(datoMateria.toString())
        primerp.setText(datoPrimerP.toString())
        segundop.setText(datoSegundoP.toString())


        btnatras.setOnClickListener {
            finish()
        }
    }
    fun operacion(){
        val bundle = intent.extras
        val resultado = findViewById<EditText>(R.id.txtResultadoFinal)

        val datoPrimerP = bundle?.getString("primerP")
        val datoSegundoP = bundle?.getString("segundoP")

        val resultadoOP = datoPrimerP.toString().toDouble() * 0.2 + datoSegundoP.toString().toDouble() * 0.2

        val OP2 = resultadoOP * .4
        val OP3 = (6 - OP2) / .6
        val calF = OP3 - resultadoOP

        if(resultadoOP.equals(4.0)){
            resultado.setText(calF.toString())
            Toast.makeText(this, "Aun se puede lograr :3", Toast.LENGTH_SHORT).show()
        }else{

            resultado.setText(calF.toString())
            Toast.makeText(this, "No se logro :/", Toast.LENGTH_SHORT).show()

        }



    }
}





