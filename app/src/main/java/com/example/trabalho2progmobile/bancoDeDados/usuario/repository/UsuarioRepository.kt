package com.example.trabalho2progmobile.bancoDeDados.usuario.repository

import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.bancoDeDados.usuario.UsuarioDao
import kotlinx.coroutines.runBlocking

class UsuarioRepository(
    private val usuarioDao: UsuarioDao
): IUsuarioRepository{
    override fun inserirUsuario(usuario: Usuario): Boolean = runBlocking{
        return@runBlocking try {
            usuarioDao.inserirUsuario(usuario)
            true
        }
        catch (e:Exception){
            false
        }
    }

    override fun buscarUsuarios(): List<Usuario> = runBlocking{
        return@runBlocking usuarioDao.getAllUsuarios()
    }

    override fun buscarUsuarioPeloEmail(email: String): Usuario = runBlocking{
        return@runBlocking usuarioDao.getUsuarioByEmail(email)
    }

    override fun buscarUsuarioPeloId(usuarioId: Int): Usuario = runBlocking{
        return@runBlocking usuarioDao.getUsuarioById(usuarioId)
    }

    override fun atualizarUsuario(usuario: Usuario): Boolean = runBlocking{
        return@runBlocking try {
            usuarioDao.updateUsuario(usuario)
            true
        }
        catch (e:Exception){
             false
        }
    }

    override fun deletarUsuario(usuario: Usuario): Boolean = runBlocking{
        return@runBlocking try {
            usuarioDao.deleteUsuario(usuario)
            true
        }
        catch (e:Exception){
            false
        }
    }
}