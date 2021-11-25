package com.example.trabalho2progmobile.bancoDeDados.usuario.repository

import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario

interface IUsuarioRepository {
    fun inserirUsuario(usuario: Usuario): Boolean
    fun buscarUsuarios(): List<Usuario>
    fun buscarUsuarioPeloEmail(email: String): Usuario
    fun buscarUsuarioPeloId(usuarioId: Int): Usuario
    fun atualizarUsuario(usuario: Usuario): Boolean
    fun deletarUsuario(usuario: Usuario): Boolean
}