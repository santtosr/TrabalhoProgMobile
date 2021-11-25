package com.example.trabalho2progmobile.bancoDeDados.usuario

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "usuario")
@Parcelize
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val usuarioId: Int = 0,
    val nome: String,
    val email: String,
    var senha: String,
    val foto: Bitmap
): Parcelable