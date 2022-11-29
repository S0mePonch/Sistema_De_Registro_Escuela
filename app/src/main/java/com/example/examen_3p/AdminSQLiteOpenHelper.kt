package com.example.examen_3p

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper
    (context: Context, name: String, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper (context,name,factory, version)
{
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE TestAlumnos(NombreAlumno text, " +
                "NombreMateria text," +
                "PrimerParcial real," +
                "SegundoParcial real)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}