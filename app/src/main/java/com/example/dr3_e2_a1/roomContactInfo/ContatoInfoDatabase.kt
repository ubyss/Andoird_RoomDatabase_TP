package com.example.dr3_e2_a1.roomContactInfo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(ContatoInfo::class),
    version = 2
)
abstract class ContatoInfoDatabase : RoomDatabase(){
    abstract fun obterContatoDAO() : ContatoInfoDao
}