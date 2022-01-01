package org.imma.appquedasimma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.room.Room
import org.imma.appquedasimma.dao.MainDatabase
import org.imma.appquedasimma.entities.UserEntity
import org.imma.appquedasimma.utils.USER

class SecondPage : AppCompatActivity() {
    companion object {
        val TAG = "SecondPage"
    }

    private lateinit var alturaEditText: EditText
    private lateinit var editTextNumberIdade: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonContinuarForms: Button
    private lateinit var database: MainDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forms_main)

        alturaEditText = findViewById(R.id.editTextNumberAltura)
        editTextNumberIdade = findViewById(R.id.editTextNumberIdade)
        radioGroup = findViewById(R.id.radioGroup)
        buttonContinuarForms = findViewById(R.id.buttonContinuarForms)
        database = Room.databaseBuilder(applicationContext, MainDatabase::class.java, "SQLITE_DATABASE")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

        radioGroup.check(R.id.radioButtonFeminino)
        buttonContinuarForms.setOnClickListener {
            val tempUser:UserEntity = intent.extras?.get(USER) as UserEntity
            val sexo = when(radioGroup.checkedRadioButtonId){
                R.id.radioButtonMasculino -> 'M'
                R.id.radioButtonFeminino -> 'F'
                else -> 'I'
            }
            tempUser.sexo = sexo
            tempUser.altura = alturaEditText.text.toString().toInt()
            tempUser.idade = editTextNumberIdade.text.toString().toInt()
        database.roomDao().insertAll(arrayListOf(tempUser))
        val users = database.roomDao().getAllFromDb()
        val user = users[users.size-1]
        Log.i(TAG, "Retirado do banco ${user.name},${user.idade} anos, ${user.sexo}, ${user.altura}cm")
        nextPage()
        }
    }
    private fun nextPage() {
        val intent = Intent(this, TermoDeAceite::class.java)
        startActivity(intent)
    }
}