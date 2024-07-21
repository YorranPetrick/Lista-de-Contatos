package com.yorran.listadecontatos

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yorran.listadecontatos.models.User

//Criando a class para armazenar o banco de dados
@Database(entities = [User::class], version = 1)
//A Class AppDatabase herdará de RooDatabase
abstract class AppDatabse: RoomDatabase() {

    //Criando um metodo abstrato para associar com a interface de acesso a dados
    abstract fun UserDao(): Dao

    companion object{
        //Criando uma variável com o nome do BD
        private const val DAATEBASE_NAME = "BD_User"

        // variavel para que irá receber a instancia do banco de dados
        @Volatile
        private var INSTANCE: AppDatabse ? = null

        //Função para pegar a instancia do BD
        fun getInstance(context: Context): AppDatabse{

            return INSTANCE ?: synchronized(this){
                //Configuração do ROOM
                val instanceDataBase = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabse::class.java,
                    DAATEBASE_NAME
                    ).build()

                INSTANCE = instanceDataBase
                instanceDataBase

            }

        }
    }
}