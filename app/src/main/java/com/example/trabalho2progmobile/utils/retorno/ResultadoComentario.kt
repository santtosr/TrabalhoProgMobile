package com.example.trabalho2progmobile.utils.retorno

import com.example.trabalho2progmobile.utils.comentarioComUsuario.ComentarioComUsuario

data class ResultadoComentario(
    val resultadoStatus: ResultadoStatus,
    val correto: Boolean,
    val comentario: ComentarioComUsuario?
){
    enum class ResultadoStatus {
        CARREGANDO, FINALIZADO
    }
}