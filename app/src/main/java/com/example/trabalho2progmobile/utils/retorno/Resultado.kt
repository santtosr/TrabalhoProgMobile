package com.example.trabalho2progmobile.utils.retorno

data class Resultado(
    val resultadoStatus: ResultadoStatus,
    val correto: Boolean
){
    enum class ResultadoStatus {
        CARREGANDO, FINALIZADO
    }
}