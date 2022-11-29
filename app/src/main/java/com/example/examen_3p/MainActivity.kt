package com.example.examen_3p

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.examen_3p.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgregar.setOnClickListener {
            subirAlumno()
        }
        binding.btnBuscar.setOnClickListener {
            buscarPorAlumno()
        }
        binding.btnEliminar.setOnClickListener {
            eliminarPorAlumno()
        }
        binding.btnModificar.setOnClickListener {
            actualizarAlumno()
        }

    }


    fun subirAlumno(){
        val dato01 = findViewById<EditText>(R.id.txtNombre)
        val dato02 = findViewById<EditText>(R.id.txtMateria)
        val dato03 = findViewById<EditText>(R.id.clfPrimerParcial)
        val dato04 = findViewById<EditText>(R.id.clfSegundoParcial)

        if(dato01.text.toString().isEmpty() || dato02.text.toString().isEmpty() || dato03.text.toString().isEmpty() || dato04.text.toString().isEmpty()){

            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()

        }else{
            val admin = AdminSQLiteOpenHelper(this,"administracionEscuela", null, 1)
            val bd = admin.writableDatabase
            val registrar = ContentValues()

            registrar.put("NombreAlumno", binding.txtNombre.text.toString())
            registrar.put("NombreMateria", binding.txtMateria.text.toString())
            registrar.put("PrimerParcial", binding.clfPrimerParcial.text.toString())
            registrar.put("SegundoParcial", binding.clfSegundoParcial.text.toString())
            bd.insert("TestAlumnos", null, registrar)

            bd.close()
            intent = Intent (this@MainActivity, ResultadosActivity::class.java)

            intent.putExtra("AlumnoN", binding.txtNombre.text.toString())
            intent.putExtra("MateriaN", binding.txtMateria.text.toString())
            intent.putExtra("primerP", binding.clfPrimerParcial.text.toString())
            intent.putExtra("segundoP", binding.clfSegundoParcial.text.toString())

            startActivity(intent)

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()

            binding.txtNombre.setText("")
            binding.txtMateria.setText("")
            binding.clfPrimerParcial.setText("")
            binding.clfSegundoParcial.setText("")
        }

    }
    fun buscarPorAlumno(){
        val admin = AdminSQLiteOpenHelper(this,"administracionEscuela", null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("SELECT NombreMateria, PrimerParcial, SegundoParcial FROM TestAlumnos WHERE NombreAlumno='${binding.txtNombre.text.toString()}'", null)

        if(fila.moveToFirst()){


            binding.txtMateria.setText(fila.getString(0))
            binding.clfPrimerParcial.setText(fila.getString(1))
            binding.clfSegundoParcial.setText(fila.getString(2))
        }else{
            Toast.makeText(this, "No hay coincidencias con el alumno", Toast.LENGTH_SHORT).show()
        }
    }
    fun eliminarPorAlumno(){
        val admin = AdminSQLiteOpenHelper(this,"administracionEscuela", null, 1)
        val bd = admin.writableDatabase
        val cant = bd.delete("TestAlumnos","NombreAlumno='${binding.txtNombre.text.toString()}'", null)

        if(cant.toString() != ""){
            Toast.makeText(this, "Alumno borrado", Toast.LENGTH_SHORT).show()

            binding.txtNombre.setText("")
            binding.txtMateria.setText("")
            binding.clfPrimerParcial.setText("")
            binding.clfSegundoParcial.setText("")
        }else{
            Toast.makeText(this, "No hay coincidencias con el alumno", Toast.LENGTH_SHORT).show()
        }
    }
    fun actualizarAlumno(){
        val admin = AdminSQLiteOpenHelper(this,"administracionEscuela", null, 1)
        val bd = admin.writableDatabase
        val actualizar = ContentValues()


        actualizar.put("NombreMateria", binding.txtMateria.text.toString())
        actualizar.put("PrimerParcial", binding.clfPrimerParcial.text.toString())
        actualizar.put("SegundoParcial", binding.clfSegundoParcial.text.toString())


        val cant = bd.update("TestAlumnos", actualizar,"NombreAlumno='${binding.txtNombre.text.toString()}'", null)

        if(cant.toString() != ""){
            Toast.makeText(this, "El alumno fue actualizado", Toast.LENGTH_SHORT).show()

            binding.txtNombre.setText("")
            binding.txtMateria.setText("")
            binding.clfPrimerParcial.setText("")
            binding.clfSegundoParcial.setText("")

        }else{
            Toast.makeText(this, "No hay coincidencias con el alumno", Toast.LENGTH_SHORT).show()
        }

    }
}