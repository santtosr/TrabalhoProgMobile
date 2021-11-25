package com.example.trabalho2progmobile.bancoDeDados.comentario

import androidx.room.*

@Dao
interface ComentarioDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun inserirComentario(comentario: Comentario)

    @Delete
    suspend fun deleteComentario(comentario: Comentario)

    @Query("SELECT * FROM comentarios where topicoId == :topicoId")
    suspend fun getAllComentarios(topicoId: Int): List<Comentario>

    @Query("SELECT * FROM comentarios WHERE comentarioId == :id")
    suspend fun getComentarioById(id: Int): Comentario
}