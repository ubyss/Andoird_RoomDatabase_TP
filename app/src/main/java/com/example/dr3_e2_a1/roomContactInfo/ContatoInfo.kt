package com.example.dr3_e2_a1.roomContactInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContatoInfo (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val email: String?,
    val adress: String?
)
