package com.example.trabalho2progmobile.aplicacao.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.bancoDeDados.usuario.UsuarioDao
import com.example.trabalho2progmobile.bancoDeDados.usuario.repository.IUsuarioRepository
import com.example.trabalho2progmobile.bancoDeDados.usuario.repository.UsuarioRepository
import com.example.trabalho2progmobile.utils.criptografia.Criptografia.Companion.encriptografarMensagem
import com.example.trabalho2progmobile.utils.login.RealizarLogin
import com.example.trabalho2progmobile.utils.mvvm.abstracts.dadosDoUsuario.AbstractDadosDoUsuarioViewModel

class LoginViewModel(
    val usuarioRepository: IUsuarioRepository
) : AbstractDadosDoUsuarioViewModel() {

    val realizarLogin: LiveData<RealizarLogin> get() = _realizarLogin
    private val _realizarLogin by lazy { MutableLiveData<RealizarLogin>() }

    fun verificarCampos(
        email: String,
        senha: String
    ){
        val verificarEmail = verificarEmail(email)
        val verificarSenhas = verificarSenha(senha)

        _dadosCorretos.value = verificarEmail && verificarSenhas
    }

    private fun verificarSenha(senha: String): Boolean{
        return if(senha.isNotEmpty()) true
        else {
            _erroSenha.value = R.string.erro_senha_vazia
            false
        }
    }

    fun buscarUsuarioParaLogin(email: String, senha: String){
        val usuario = usuarioRepository.buscarUsuarioPeloEmail(email)
        if(usuario != null){
            _realizarLogin.value = RealizarLogin(
                realizar = usuario.senha == encriptografarMensagem(senha),
                usuario = usuario
            )
        }
        else{
            _realizarLogin.value = RealizarLogin(
                realizar = false,
                usuario = null
            )
        }
    }

}