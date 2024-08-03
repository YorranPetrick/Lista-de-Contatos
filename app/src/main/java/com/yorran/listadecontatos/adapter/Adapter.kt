package com.yorran.listadecontatos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        //Recuperando as informações contidas na listaContatos e passando aos campos correspondentes
        holder.idFirstNamee.text = listaContato[position].firstName
        holder.idNomeFamily.text = listaContato[position].familyName
        holder.idNumber.text = "(${listaContato[position].ddd}) ${listaContato[position].telephone}"
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

