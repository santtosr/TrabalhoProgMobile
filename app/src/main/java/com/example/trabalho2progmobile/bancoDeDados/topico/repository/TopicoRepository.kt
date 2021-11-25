package com.example.trabalho2progmobile.bancoDeDados.topico.repository

import com.example.trabalho2progmobile.bancoDeDados.topico.Topico
import com.example.trabalho2progmobile.bancoDeDados.topico.TopicoDao
import kotlinx.coroutines.runBlocking

class TopicoRepository(
    private val topicoDao: TopicoDao
): ITopicoRepository{
    override fun inserirTopico(topico: Topico): Boolean = runBlocking{
        return@runBlocking try {
            topicoDao.inserirTopico(topico)
            true
        }
        catch (e:Exception){
            false
        }
    }

    override fun buscarTopicos(): List<Topico> = runBlocking{
        return@runBlocking topicoDao.getAllTopicos()
    }

    override fun buscarTopicoPeloId(topicoId: Int): Topico = runBlocking{
        return@runBlocking topicoDao.getTopicoById(topicoId)
    }

    override fun deletarTopico(topico: Topico): Boolean = runBlocking{
        return@runBlocking try {
            topicoDao.deleteTopico(topico)
            true
        }
        catch (e:Exception){
            false
        }
    }
}