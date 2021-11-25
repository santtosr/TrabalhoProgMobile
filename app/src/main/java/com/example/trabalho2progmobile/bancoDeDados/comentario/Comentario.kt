package com.example.trabalho2progmobile.bancoDeDados.comentario

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "comentarios")
data class Comentario(
    @PrimaryKey(autoGenerate = true)
    val comentarioId: Int = 0,
    val text : String,
    val dataHora: String,
    val usuarioId : Int,
    val topicoId : Int
): Parcelable