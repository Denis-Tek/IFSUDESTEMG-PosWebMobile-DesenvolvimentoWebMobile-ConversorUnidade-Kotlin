package com.example.conversordeunidades

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editQuantidade        = findViewById<EditText>(R.id.editQuantidade)
        val spinnerUnidadeDestino = findViewById<Spinner>(R.id.spinnerUnidadeDestino)
        val spinnerUnidadeOrigem  = findViewById<Spinner>(R.id.spinnerUnidadeOrigem)
        val botaoConverter        = findViewById<Button>(R.id.botaoConverter)
        val labelResultado        = findViewById<TextView>(R.id.labelResultado)

        val adapter = ArrayAdapter.createFromResource(this, R.array.unidades, android.R.layout.simple_spinner_dropdown_item)

        spinnerUnidadeDestino.adapter = adapter
        spinnerUnidadeOrigem.adapter  = adapter

        botaoConverter.setOnClickListener{
            val sQuantidade = editQuantidade.text.toString()
            if (sQuantidade.isEmpty()) {
                Toast.makeText(this, "Informe a Quantidade", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var resultado: Double = sQuantidade.toDouble()
            val unidadeOrigem  = spinnerUnidadeOrigem.selectedItem.toString()
            val unidadeDestino = spinnerUnidadeDestino.selectedItem.toString()

            val toMetro = when (unidadeOrigem) {
                "mm"  -> 0.001
                "cm"  -> 0.01
                "dm"  -> 0.1
                "m"   -> 1
                "dam" -> 10
                "hm"  -> 100
                "km"  -> 1000
                else  -> 0
            }

            val toDestino = when (unidadeDestino) {
                "mm"  -> 1000
                "cm"  -> 100
                "dm"  -> 10
                "m"   -> 1
                "dam" -> 0.1
                "hm"  -> 0.01
                "km"  -> 0.001
                else  -> 0
            }

            resultado *= toMetro.toDouble() * toDestino.toDouble()

            labelResultado.text = resultado.toString()
        }
    }
}