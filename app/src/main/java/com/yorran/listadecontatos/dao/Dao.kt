package com.yorran.listadecontatos.dao

import androidx.room.Dao
import androidx.room.Insert
import com.yorran.listadecontatos.models.User

//Assinatura para Informar que a interface é um Objeto de Acesso
@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: MutableList<User>)
}