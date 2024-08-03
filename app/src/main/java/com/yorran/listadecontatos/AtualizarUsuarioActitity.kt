package com.yorran.listadecontatos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yorran.listadecontatos.databinding.ActivityAtualizarUsuarioBinding
import com.yorran.listadecontatos.databinding.ActivityContactBinding

class AtualizarUsuarioActitity : AppCompatActivity() {

    lateinit var binding: ActivityAtualizarUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAtualizarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        completarCampos(binding)


    }

    private fun completarCampos(binding : ActivityAtualizarUsuarioBinding){
        //Variaveis para pegar os dados do putExtra criado no Adapter
        val firstName = intent.getStringExtra("firstName")
        val familyNme = intent.getStringExtra("familyName")
        val ddd = intent.getStringExtra("ddd")
        val telephone = intent.getStringExtra("telephone")

        //Passando os dados para os campos

        binding.FirstName.setText(firstName)
        binding.FamilyName.setText(familyNme)
        binding.DDDFone.setText(ddd)
        binding.Telephone.setText(telephone)


    }
}