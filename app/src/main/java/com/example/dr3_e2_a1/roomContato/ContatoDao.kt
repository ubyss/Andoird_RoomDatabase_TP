package com.example.dr3_e2_a1.roomContato

import androidx.room.*

@Dao
interface ContatoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg contato: Contato)

    @Query("SELECT * FROM contato")
    fun listar(): List<Contato>

    @Delete
    fun delete(contato: Contato)

    @Update
    fun update(contato :Contato)


}