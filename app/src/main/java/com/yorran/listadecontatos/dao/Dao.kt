package com.yorran.listadecontatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yorran.listadecontatos.models.User

//Assinatura para Informar que a interface é um Objeto de Acesso
@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: MutableList<User>)

    @Query("SELECT * FROM Table_User ORDER BY FirstName ASC")
    suspend fun getAll() : MutableList<User>
}