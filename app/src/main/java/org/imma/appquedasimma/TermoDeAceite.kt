package org.imma.appquedasimma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TermoDeAceite : AppCompatActivity() {

    private lateinit var btnAceitar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.term_main)

        btnAceitar = findViewById(R.id.btnAceitar)
        btnAceitar.setOnClickListener {
            nextPage()
        }
    }

    private fun nextPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}