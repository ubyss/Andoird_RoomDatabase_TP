package com.example.dr3_e2_a1.roomContato

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Contato::class),
    version = 4
)
abstract class ContatoDatabase : RoomDatabase(){
    abstract fun obterContatoDAO() : ContatoDao
}