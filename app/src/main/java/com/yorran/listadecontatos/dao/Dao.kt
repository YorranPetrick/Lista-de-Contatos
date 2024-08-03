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
    @Query("UPDATE Table_User SET FamilyName = :updateFamilyName, FirstName = :updateFirstName, Telephone = :updateTelephone, DDD = :updateDDD WHERE id = :idUser")
    suspend fun updateUser(idUser : Int, updateFamilyName: String, updateFirstName: String, updateTelephone: String, updateDDD: String)
}