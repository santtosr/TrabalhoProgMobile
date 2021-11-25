package com.example.trabalho2progmobile.bancoDeDados.comentario.repository

import com.example.trabalho2progmobile.bancoDeDados.comentario.Comentario
import com.example.trabalho2progmobile.bancoDeDados.comentario.ComentarioDao
import kotlinx.coroutines.runBlocking

class ComentarioRepository(
    private val comentarioDao: ComentarioDao
): IComentarioRepository{
    override fun inserirComentario(comentario: Comentario): Boolean = runBlocking{
        return@runBlocking try {
            comentarioDao.inserirComentario(comentario)
            true
        }
        catch (e:Exception){
            false
        }
    }

    override fun buscarComentarios(topicoId: Int): List<Comentario> = runBlocking{
        return@runBlocking comentarioDao.getAllComentarios(topicoId)
    }

    override fun buscarComentarioPeloId(comentarioId: Int): Comentario = runBlocking{
        return@runBlocking comentarioDao.getComentarioById(comentarioId)
    }

    override fun deletarComentario(comentario: Comentario): Boolean = runBlocking{
        return@runBlocking try {
            comentarioDao.deleteComentario(comentario)
            true
        }
        catch (e:Exception){
            false
        }
    }
}