package com.yorran.listadecontatos

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yorran.listadecontatos.databinding.ActivityAddContactBinding

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.addContactBtn.setOnClickListener {

            val firstName = binding.FirstName.text.toString()
            val familyName = binding.FamilyName.text.toString()
            val dddPhone = binding.DDDFone.text.toString()
            val telephone = binding.Telephone.text.toString()

            verificarCampos(firstName, familyName, dddPhone, telephone )
        }
    }

    private fun verificarCampos(firstName: String, familyName: String, dddPhone: String, telephone: String){
        if(firstName.isEmpty() || familyName.isEmpty() || dddPhone.isEmpty() || telephone.isEmpty()){

            Toast.makeText(this, "Todos os Campos Precisam ser Preenchidos", Toast.LENGTH_LONG).show()
        }
    }

}