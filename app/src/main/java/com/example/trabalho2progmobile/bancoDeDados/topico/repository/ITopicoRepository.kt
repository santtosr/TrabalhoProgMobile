package com.example.trabalho2progmobile.bancoDeDados.topico.repository

import com.example.trabalho2progmobile.bancoDeDados.topico.Topico

interface ITopicoRepository {
    fun inserirTopico(topico: Topico): Boolean
    fun buscarTopicos(): List<Topico>
    fun buscarTopicoPeloId(topicoId: Int): Topico
    fun deletarTopico(topico: Topico): Boolean
}