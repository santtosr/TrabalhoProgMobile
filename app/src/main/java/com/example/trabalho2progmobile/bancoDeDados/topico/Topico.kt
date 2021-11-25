package com.example.trabalho2progmobile.bancoDeDados.topico

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "topicos")
data class Topico(
    @PrimaryKey(autoGenerate = true)
    val topicoId: Int = 0,
    val nome: String,
    val hora: String,
    val descricao: String,
): Parcelable