package com.example.dr3_e2_a1.roomContactInfo

import androidx.room.*
import com.example.dr3_e2_a1.roomContato.Contato

@Dao
interface ContatoInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg contatoInfo: ContatoInfo)

    @Query("SELECT * FROM contatoinfo")
    fun listar(): List<ContatoInfo>

    @Delete
    fun delete(contatoInfo: ContatoInfo)


    @Update
    fun update(contatoInfo: ContatoInfo)

}