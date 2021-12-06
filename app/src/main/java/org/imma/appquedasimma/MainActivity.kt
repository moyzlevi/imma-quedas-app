package org.imma.appquedasimma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import org.imma.appquedasimma.dao.MainDatabase
import org.imma.appquedasimma.entities.UserEntity

class MainActivity : AppCompatActivity() {

    private lateinit var continuarButton: Button
    private lateinit var nomeEditText: EditText
    private lateinit var database:MainDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        continuarButton = findViewById(R.id.buttonContinuar)
        nomeEditText = findViewById(R.id.editTextNome)
        database = Room.databaseBuilder(applicationContext,MainDatabase::class.java,"SQLITE_DATABASE")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

        var nomeInput: String
        continuarButton.setOnClickListener {
            Log.i("Main","Clicou no botao continuar")
            nomeInput = nomeEditText.text.toString()

            if(nomeInput != ""){
                database.roomDao().insertAll(arrayListOf((UserEntity(0,nomeInput))))
                Log.i("Main","Retirado do banco: "+ database.roomDao().getAllFromDb().get(0).name.toString())
            }

        }



    }

}