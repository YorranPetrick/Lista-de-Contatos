package com.yorran.listadecontatos.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yorran.listadecontatos.AtualizarUsuarioActitity
import com.yorran.listadecontatos.databinding.ActivityContactBinding
import com.yorran.listadecontatos.models.User

class Adapter(private val context: Context, private val listaContato: MutableList<User>):
    RecyclerView.Adapter<Adapter.ContatosViewHolder>() {

        //Responsável por Inflar o Layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {

        val itemList = ActivityContactBinding.inflate(LayoutInflater.from(context), parent, false)
        return ContatosViewHolder(itemList)

    }

    //Responsável por exibir os itens criados pelo metodo onCreateViewHolder
    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
        val firstName = listaContato[position].firstName
        val familyName = listaContato[position].familyName
        val ddd = listaContato[position].ddd
        val telephone = listaContato[position].telephone

        //Recuperando as informações contidas na listaContatos e passando aos campos correspondentes
        holder.idFirstNamee.text = firstName
        holder.idNomeFamily.text = familyName
        holder.idNumber.text = ("${(ddd)} $telephone")

        //Criando evento de clique no icone de atualizar
        holder.idEditBtn.setOnClickListener {
            val intent = Intent(context, AtualizarUsuarioActitity::class.java)
            //Passando os dados para a proxima tela com o PutExtra
            intent.putExtra("firstName", firstName)
            intent.putExtra("familyName",familyName)
            intent.putExtra("ddd", ddd)
            intent.putExtra("telephone", telephone)
            intent.putExtra("id", listaContato[position].id)
            //Inicializado a Activity
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = listaContato.size




    inner class ContatosViewHolder(binding:ActivityContactBinding) : RecyclerView.ViewHolder(binding.root) {
        val idNomeFamily = binding.FamilyName
        val idFirstNamee = binding.FirstName
        val idNumber = binding.Number
        val idEditBtn = binding.EditButton
        val idDeletBtn = binding.DeleteButton

    }
}

