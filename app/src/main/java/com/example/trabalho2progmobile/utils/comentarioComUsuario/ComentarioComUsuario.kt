package com.example.trabalho2progmobile.utils.comentarioComUsuario

import com.example.trabalho2progmobile.bancoDeDados.comentario.Comentario
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario

data class ComentarioComUsuario(
    val comentario: Comentario,
    val usuario: Usuario
)