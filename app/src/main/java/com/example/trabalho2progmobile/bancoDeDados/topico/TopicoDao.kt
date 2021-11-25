package com.example.trabalho2progmobile.bancoDeDados.topico

import androidx.room.*

@Dao
interface TopicoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserirTopico(topico: Topico)

    @Delete
    suspend fun deleteTopico(topico: Topico)

    @Query("SELECT * FROM topicos")
    suspend fun getAllTopicos(): List<Topico>

    @Query("SELECT * FROM topicos WHERE topicoId == :id")
    suspend fun getTopicoById(id: Int): Topico
}