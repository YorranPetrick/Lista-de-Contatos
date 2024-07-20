package com.yorran.listadecontatos.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Anotação do Room para mostrar que essa class é uma entidade
@Entity(tableName = "Table_User")
data class User(
    @ColumnInfo(name = "FamilyName") val familyName : String,
    @ColumnInfo(name = "FirstName") val firstName : String,
    @ColumnInfo(name = "Telephone") val telephone : String


){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}