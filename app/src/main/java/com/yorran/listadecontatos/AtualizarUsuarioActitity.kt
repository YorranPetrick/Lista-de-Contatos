package com.yorran.listadecontatos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yorran.listadecontatos.dao.UserDao
import com.yorran.listadecontatos.databinding.ActivityAtualizarUsuarioBinding
import com.yorran.listadecontatos.databinding.ActivityContactBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AtualizarUsuarioActitity : AppCompatActivity() {

    lateinit var binding: ActivityAtualizarUsuarioBinding
    //Variável que receberá a instancia do banco de dados
    lateinit var userDao: UserDao
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

        binding.updateContactBtn.setOnClickListener {

            var firstName = binding.FirstName.text.toString()
            var familyName = binding.FamilyName.text.toString()
            var telephone = binding.Telephone.text.toString()
            var dddPhone = binding.DDDFone.text.toString()

            if (firstName.isEmpty() || familyName.isEmpty() || dddPhone.isEmpty() || telephone.isEmpty()) {

                Toast.makeText(this, "Todos os Campos Precisam ser Preenchidos", Toast.LENGTH_LONG)
                    .show()
            }
            else {

                atualizarUsuario(
                    firstName,
                    familyName,
                    telephone,
                    dddPhone
                )
            }
        }


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

    private fun atualizarUsuario(firstName: String, familyName: String, telephone: String, ddd: String){
        //Atribuindo a instancia do banco de dados a variavel userDao
        userDao = AppDatabase.getInstance(this).userDao()
        var id = intent.getIntExtra("id", -1)

        if (id == -1){
            Toast.makeText(this, "Identificador do Usuário não encontrado", Toast.LENGTH_LONG).show()
        }else{

            CoroutineScope(Dispatchers.IO).launch {
                userDao.updateUser(
                    id,
                    familyName,
                    firstName,
                    telephone,
                    ddd
                )
            }
            Toast.makeText(this, "Atualizado com Sucesso", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}