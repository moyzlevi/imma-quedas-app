package org.imma.appquedasimma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.room.Room
import org.imma.appquedasimma.dao.MainDatabase
import org.imma.appquedasimma.entities.UserEntity

class MainActivity : AppCompatActivity() {

    private lateinit var continuarButton: Button
    private lateinit var nomeEditText: EditText
    private lateinit var database: MainDatabase
//    private var alturaEditText: EditText = findViewById(R.id.editTextNumberAltura)
//    private var editTextNumberIdade: EditText = findViewById(R.id.editTextNumberIdade)
//    private var masculinoRadioButton:RadioButton = findViewById(R.id.radioButtonMasculino)
//    private var femininoRadioButton: RadioButton = findViewById(R.id.radioButtonFeminino)
//    private var buttonContinuarForms: Button = findViewById(R.id.buttonContinuarForms)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        continuarButton = findViewById(R.id.buttonContinuar)
        nomeEditText = findViewById(R.id.editTextNome)

        database = Room.databaseBuilder(applicationContext, MainDatabase::class.java, "SQLITE_DATABASE")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

        var tempUserEntity: UserEntity = UserEntity()
        continuarButton.setOnClickListener {
            Log.i("Main", "Clicou no botao continuar")
            tempUserEntity.name = nomeEditText.text.toString()

            if (tempUserEntity.name != "") {
                setContentView(R.layout.forms_main)

            }
        }

//        buttonContinuarForms.setOnClickListener {
//            Log.i("Forms", "Clicou no botao continuar forms")
//            if (alturaEditText.text.toString() != "" && editTextNumberIdade.text.toString() != "" && (checkMasculinoRadioGroup() || checkFemininoRadioGroup())) {
//                tempUserEntity.altura = alturaEditText.text.toString().toInt()
//                tempUserEntity.idade = editTextNumberIdade.text.toString().toInt()
//                if(checkMasculinoRadioGroup()){
//                    tempUserEntity.sexo = 'M'
//                }else{
//                    tempUserEntity.sexo = 'F'
//                }
//            }
//        database.roomDao().insertAll(arrayListOf(tempUserEntity))
//
//        Log.i("Forms", "Salvo no banco "+tempUserEntity)
//        Log.i("Forms", "Retirado do banco "+database.roomDao().getAllFromDb().get(0))
//        }
//    }
//
//    private fun checkMasculinoRadioGroup(): Boolean {
//        return masculinoRadioButton.isChecked && !femininoRadioButton.isChecked
//    }
//
//    private fun checkFemininoRadioGroup(): Boolean {
//        return !masculinoRadioButton.isChecked && femininoRadioButton.isChecked
//    }
}}

