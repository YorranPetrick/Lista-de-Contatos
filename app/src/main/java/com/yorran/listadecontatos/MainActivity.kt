package com.yorran.listadecontatos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.yorran.listadecontatos.adapter.Adapter
import com.yorran.listadecontatos.dao.UserDao
import com.yorran.listadecontatos.databinding.ActivityMainBinding
import com.yorran.listadecontatos.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

   private lateinit var binding : ActivityMainBinding
   private lateinit var usuarioDao: UserDao
   //Variavel que irá receber os usuários
   private var listaUsuario : MutableList<User> = mutableListOf()
    private var _listaObservada = MutableLiveData<MutableList<User>>()
    //Adapter
    private lateinit var contatoAdapter: Adapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.AddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        CoroutineScope(Dispatchers.IO).launch {
            getContact()

            withContext(Dispatchers.Main){

                _listaObservada.observe(this@MainActivity){ usuarioLista ->

                    val recyclerViewContact = binding.ViewContact
                    recyclerViewContact.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerViewContact.setHasFixedSize(true)

                    //Trabalhando no adapter
                    contatoAdapter = Adapter(this@MainActivity, usuarioLista)

                    recyclerViewContact.adapter = contatoAdapter
                    //Notificando a lista com novas alterações
                    contatoAdapter.notifyDataSetChanged()
                }

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        //Função para trabalhar a lista no ciclo de vida
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            getContact()

            withContext(Dispatchers.Main){

                _listaObservada.observe(this@MainActivity){ usuarioLista ->

                    val recyclerViewContact = binding.ViewContact
                    recyclerViewContact.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerViewContact.setHasFixedSize(true)

                    //Trabalhando no adapter
                    contatoAdapter = Adapter(this@MainActivity, usuarioLista)

                    recyclerViewContact.adapter = contatoAdapter
                    //Notificando a lista com novas alterações
                    contatoAdapter.notifyDataSetChanged()
                }

            }
        }


    }

    private fun getContact(){
        usuarioDao = AppDatabase.getInstance(this).userDao()
        CoroutineScope(Dispatchers.IO).launch {
           listaUsuario =  usuarioDao.getAll()
            _listaObservada.postValue(listaUsuario)
        }
    }
}