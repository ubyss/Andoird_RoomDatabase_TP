package com.example.dr3_e2_a1.roomContato

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contato (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val nome: String?,
    val Fone: String?
)
