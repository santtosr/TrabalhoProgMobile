package com.example.trabalho2progmobile.utils.login

import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario

data class RealizarLogin(
    val realizar: Boolean,
    val usuario: Usuario?
)
