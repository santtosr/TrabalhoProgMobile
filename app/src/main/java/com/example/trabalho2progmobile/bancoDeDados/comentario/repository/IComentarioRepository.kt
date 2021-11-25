package com.example.trabalho2progmobile.bancoDeDados.comentario.repository

import com.example.trabalho2progmobile.bancoDeDados.comentario.Comentario

interface IComentarioRepository {
    fun inserirComentario(comentario: Comentario): Boolean
    fun buscarComentarios(topicoId: Int): List<Comentario>
    fun buscarComentarioPeloId(comentarioId: Int): Comentario
    fun deletarComentario(comentario: Comentario): Boolean
}