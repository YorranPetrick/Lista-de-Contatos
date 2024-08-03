package com.yorran.listadecontatos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yorran.listadecontatos.dao.UserDao
import com.yorran.listadecontatos.databinding.ActivityAddContactBinding
import com.yorran.listadecontatos.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    var listaUsuarios: MutableList<User> = mutableListOf()
    lateinit var userDao: UserDao

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

            verificarCampos(firstName, familyName, dddPhone, telephone)
            finish()
        }


    }

    private fun verificarCampos(
        firstName: String,
        familyName: String,
        dddPhone: String,
        telephone: String
    ) {
        if (firstName.isEmpty() || familyName.isEmpty() || dddPhone.isEmpty() || telephone.isEmpty()) {

            Toast.makeText(this, "Todos os Campos Precisam ser Preenchidos", Toast.LENGTH_LONG)
                .show()
        } else {
            cadastrar(firstName, familyName, dddPhone, telephone)

            //Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show()
            //finish()
        }
    }

    private fun cadastrar(
        firstName: String,
        familyName: String,
        dddPhone: String,
        telephone: String
    ) {
        //Variavel para receber o obj usuário


        CoroutineScope(Dispatchers.IO).launch {
            val user = User(familyName, firstName, telephone, dddPhone)
            listaUsuarios.add(user)
            userDao = AppDatabase.getInstance(applicationContext).userDao()
            try {
                userDao.insert(listaUsuarios)

                // Trocar para o contexto principal para operações de UI
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddContactActivity,
                        "Cadastrado com sucesso",
                        Toast.LENGTH_LONG
                    ).show()

                }
            } catch (e: Exception) {
                // Log de erro
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddContactActivity,
                        "Erro ao cadastrar: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}