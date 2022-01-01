package org.imma.appquedasimma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import org.imma.appquedasimma.dao.MainDatabase
import org.imma.appquedasimma.entities.UserEntity
import org.imma.appquedasimma.utils.USER

class FirstPage : AppCompatActivity() {
    companion object {
        val TAG = "FirstPage"
    }

    private lateinit var continuarButton: Button
    private lateinit var nomeEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        continuarButton = findViewById(R.id.buttonContinuar)
        nomeEditText = findViewById(R.id.editTextNome)

        var tempUserEntity: UserEntity = UserEntity()
        continuarButton.setOnClickListener {
            Log.i("Main", "Clicou no botao continuar")
            tempUserEntity.name = nomeEditText.text.toString()

            nextPage(tempUserEntity)
        }
    }

    private fun nextPage(user: UserEntity) {
        val intent = Intent(this, SecondPage::class.java)
        intent.putExtra(USER, user)
        startActivity(intent)
    }
}

